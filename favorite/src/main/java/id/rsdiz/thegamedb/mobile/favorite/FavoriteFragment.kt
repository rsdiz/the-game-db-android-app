package id.rsdiz.thegamedb.mobile.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.rsdiz.thegamedb.core.ui.GameAdapter
import id.rsdiz.thegamedb.mobile.di.FavoriteModule
import id.rsdiz.thegamedb.mobile.favorite.databinding.FragmentFavoriteBinding
import id.rsdiz.thegamedb.mobile.favorite.vm.FavoriteViewModel
import id.rsdiz.thegamedb.mobile.favorite.vm.ViewModelFactory
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels { vmFactory }

    private var _favoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _favoriteBinding as FragmentFavoriteBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    FavoriteModule::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _favoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameAdapter = GameAdapter()

        gameAdapter.setOnItemClickListener {
            val directions = FavoriteFragmentDirections.navigateToDetailActivity(it.id)
            findNavController().navigate(directions)
        }

        favoriteViewModel.favoriteGames.observe(viewLifecycleOwner) { results ->
            binding.apply {
                if (results.isNotEmpty()) {
                    rvFavoriteGame.visibility = View.VISIBLE
                    labelEmpty.visibility = View.GONE
                } else {
                    rvFavoriteGame.visibility = View.GONE
                    labelEmpty.visibility = View.VISIBLE
                }
            }
            gameAdapter.setGames(results)
        }

        with(binding.rvFavoriteGame) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _favoriteBinding = null
    }
}
