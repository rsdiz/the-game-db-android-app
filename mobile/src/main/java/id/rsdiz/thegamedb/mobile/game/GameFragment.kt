package id.rsdiz.thegamedb.mobile.game

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.ui.GameAdapter
import id.rsdiz.thegamedb.core.utils.autoCleared
import id.rsdiz.thegamedb.mobile.databinding.GameFragmentBinding
import id.rsdiz.thegamedb.mobile.home.IOnBackPressed
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameFragment : Fragment(), IOnBackPressed {
    private var _gameBinding: GameFragmentBinding by autoCleared()
    private val binding get() = _gameBinding

    private val gameViewModel: GameViewModel by viewModels()

    private val gameAdapter = GameAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _gameBinding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeGames(gameViewModel.games)

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        with(binding.searchGame) {
            val searchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            setIconifiedByDefault(false)
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    showLoading(true)

                    observeGames(gameViewModel.searchResult)

                    query?.let {
                        lifecycleScope.launch {
                            gameViewModel.searchGames(it)
                        }
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })

            setOnCloseListener {
                onActionViewCollapsed()
                observeGames(gameViewModel.games)
                true
            }
        }
    }

    override fun onBackPressed(): Boolean {
        with(binding) {
            return if (searchGame.query.isNotEmpty()) {
                searchGame.setQuery("", false)
                searchGame.clearFocus()
                true
            } else {
                false
            }
        }
    }

    private fun observeGames(livedata: LiveData<Resource<List<Game>>>) {
        livedata.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading(true)
                is Resource.Error -> {
                    showLoading(false)
                    binding.labelError.apply {
                        visibility = View.VISIBLE
                        text = resource.message
                    }
                }
                is Resource.Success -> {
                    resource.data?.let {
                        gameAdapter.setGames(it)
                        showLoading(false)
                    }
                }
            }
        }
    }

    private fun showLoading(state: Boolean) =
        with(binding) {
            if (state) {
                progressBar.visibility = View.VISIBLE
                labelError.visibility = View.GONE
                rvGame.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                labelError.visibility = View.GONE
                rvGame.visibility = View.VISIBLE
            }
        }
}
