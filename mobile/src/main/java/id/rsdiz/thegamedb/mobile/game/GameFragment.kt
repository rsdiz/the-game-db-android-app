package id.rsdiz.thegamedb.mobile.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.core.utils.autoCleared
import id.rsdiz.thegamedb.mobile.databinding.GameFragmentBinding

@AndroidEntryPoint
class GameFragment : Fragment() {
    private var _gameBinding: GameFragmentBinding by autoCleared()
    private val binding get() = _gameBinding

    private val gameViewModel: GameViewModel by viewModels()

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _gameBinding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}
