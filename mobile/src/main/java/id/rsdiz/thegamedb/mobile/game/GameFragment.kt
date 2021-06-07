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
import androidx.navigation.fragment.findNavController
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

    private var isSearching = false

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

        gameAdapter.setOnItemClickListener {
            if (isSearching) {
                lifecycleScope.launch {
                    gameViewModel.insertGame(it)
                }
            }
            val directions = GameFragmentDirections.navigateToDetailActivity(it.id)
            findNavController().navigate(directions)
        }

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
        }

        binding.scrollLayout.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY + 12) binding.fabToTop.hide()
            if (scrollY < oldScrollY - 12) binding.fabToTop.show()
            if (scrollY == 0) binding.fabToTop.hide()
        }

        binding.fabToTop.setOnClickListener {
            val positionY = binding.rvGame.getChildAt(0).y
            binding.scrollLayout.smoothScrollTo(0, positionY.toInt(), 1000)
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
                            isSearching = true
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
                isSearching = false
                true
            }
        }
    }

    override fun onBackPressed(): Boolean {
        with(binding) {
            return when {
                searchGame.query.isNotEmpty() -> {
                    searchGame.setQuery("", false)
                    searchGame.clearFocus()
                    true
                }
                isSearching -> {
                    observeGames(gameViewModel.games)
                    isSearching = false
                    true
                }
                else -> false
            }
        }
    }

    private fun observeGames(livedata: LiveData<Resource<List<Game>>>) {
        livedata.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> showLoading(true)
                is Resource.Error -> {
                    showLoading(false)
                    binding.fabToTop.visibility = View.GONE
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
                fabToTop.visibility = View.GONE
                labelError.visibility = View.GONE
                rvGame.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                labelError.visibility = View.GONE
                rvGame.visibility = View.VISIBLE
                fabToTop.visibility = View.VISIBLE
            }
        }
}
