package id.rsdiz.thegamedb.mobile.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.ui.GameAdapter
import id.rsdiz.thegamedb.core.utils.autoCleared
import id.rsdiz.thegamedb.mobile.databinding.GameFragmentBinding

@AndroidEntryPoint
class GameFragment : Fragment() {
    private var _gameBinding: GameFragmentBinding by autoCleared()
    private val binding get() = _gameBinding

    private val gameViewModel: GameViewModel by viewModels()

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

        val gameAdapter = GameAdapter()

        gameViewModel.games.observe(viewLifecycleOwner) { resource ->
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

        with(binding.rvGame) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = gameAdapter
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
