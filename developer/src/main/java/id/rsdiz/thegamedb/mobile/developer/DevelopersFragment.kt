package id.rsdiz.thegamedb.mobile.developer

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
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.mobile.developer.adapter.DevelopersAdapter
import id.rsdiz.thegamedb.mobile.developer.databinding.FragmentDevelopersBinding
import id.rsdiz.thegamedb.mobile.developer.vm.DeveloperViewModel
import id.rsdiz.thegamedb.mobile.developer.vm.ViewModelFactory
import id.rsdiz.thegamedb.mobile.di.DeveloperModule
import javax.inject.Inject

class DevelopersFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val developerViewModel: DeveloperViewModel by viewModels { vmFactory }

    private var _developersBinding: FragmentDevelopersBinding? = null
    private val binding get() = _developersBinding as FragmentDevelopersBinding

    private val developersAdapter = DevelopersAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerDeveloperComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context.applicationContext,
                    DeveloperModule::class.java
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
        _developersBinding = FragmentDevelopersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeDevelopers()

        developersAdapter.setOnItemClickListener {
            val directions = DevelopersFragmentDirections.navigateToDetailDevelopersActivity(it)
            findNavController().navigate(directions)
        }

        with(binding.rvDevelopers) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = developersAdapter
        }
    }

    private fun observeDevelopers() {
        developerViewModel.developers.observe(viewLifecycleOwner) { resource ->
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
                        developersAdapter.setDevelopers(it)
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
                rvDevelopers.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                labelError.visibility = View.GONE
                rvDevelopers.visibility = View.VISIBLE
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _developersBinding = null
    }
}
