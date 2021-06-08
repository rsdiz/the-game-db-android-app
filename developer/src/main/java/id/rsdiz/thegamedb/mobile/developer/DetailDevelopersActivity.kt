package id.rsdiz.thegamedb.mobile.developer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.ui.GameAdapter
import id.rsdiz.thegamedb.mobile.detail.DetailActivity
import id.rsdiz.thegamedb.mobile.developer.databinding.ActivityDetailDevelopersBinding
import id.rsdiz.thegamedb.mobile.developer.vm.DeveloperViewModel
import id.rsdiz.thegamedb.mobile.developer.vm.ViewModelFactory
import id.rsdiz.thegamedb.mobile.di.DeveloperModule
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailDevelopersActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val developerViewModel: DeveloperViewModel by viewModels { vmFactory }
    private val args: DetailDevelopersActivityArgs by navArgs()

    private var _detailDevBinding: ActivityDetailDevelopersBinding? = null
    private val binding get() = _detailDevBinding as ActivityDetailDevelopersBinding

    private var data: Developers? = null
    private val gameAdapter = GameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDeveloperComponent.builder()
            .context(applicationContext)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    DeveloperModule::class.java
                )
            )
            .build()
            .inject(this)

        _detailDevBinding = ActivityDetailDevelopersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        gameAdapter.setOnItemClickListener {
            lifecycleScope.launch {
                developerViewModel.insertGame(it)
            }

            Intent(this, DetailActivity::class.java).let { intent ->
                intent.putExtra("gameId", it.id)
                startActivity(intent)
            }
        }

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        data = args.developers

        data?.let {
            developerViewModel.getDetailDeveloper(it.id).observe(this) { resource ->
                when (resource) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        data = resource.data as Developers
                        data?.let { developers ->
                            populateData(developers)
                        }
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        binding.labelError.apply {
                            visibility = View.VISIBLE
                            text = resource.message
                        }
                        showLoading(false)
                    }
                }
            }

            developerViewModel.gameByDeveloperResult.observe(this) { resource ->
                when (resource) {
                    is Resource.Loading -> showLoadingRv(true)
                    is Resource.Error -> {
                        showLoadingRv(false)
                        binding.labelErrorRv.apply {
                            visibility = View.VISIBLE
                            text = resource.message
                        }
                    }
                    is Resource.Success -> {
                        resource.data?.let { games ->
                            gameAdapter.setGames(games)
                            showLoadingRv(false)
                        }
                    }
                }
            }
        }
    }

    private fun populateData(data: Developers) {
        binding.apply {
            supportActionBar?.subtitle = data.name
            if (data.description.isNullOrEmpty()) {
                developersDescription.visibility = View.GONE
                labelAbout.visibility = View.GONE
            } else developersDescription.text = data.description
            developersGamesCount.text = getString(R.string.games_count_value, data.gamesCount)

            lifecycleScope.launch {
                developerViewModel.getGameByDevelopers(data.slug)
            }
        }
    }

    private fun showLoading(state: Boolean) =
        binding.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
                appBar.visibility = View.GONE
                labelError.visibility = View.GONE
                scrollLayout.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                labelError.visibility = View.GONE
                scrollLayout.visibility = View.VISIBLE
                appBar.visibility = View.VISIBLE
            }
        }

    private fun showLoadingRv(state: Boolean) =
        binding.apply {
            if (state) {
                progressBarRv.visibility = View.VISIBLE
                labelErrorRv.visibility = View.GONE
                rvGame.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                labelError.visibility = View.GONE
                rvGame.visibility = View.VISIBLE
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvGame.adapter = null
        _detailDevBinding = null
    }
}
