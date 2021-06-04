package id.rsdiz.thegamedb.mobile.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.mobile.R
import id.rsdiz.thegamedb.mobile.databinding.ActivityHomepageBinding

@AndroidEntryPoint
class HomepageActivity : AppCompatActivity() {
    private var _homepageBinding: ActivityHomepageBinding? = null
    private val binding get() = _homepageBinding as ActivityHomepageBinding

    private var _navHostFragment: NavHostFragment? = null
    private val navHostFragment get() = _navHostFragment as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _homepageBinding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_homepage)
            as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navHostFragment.navController
        )
    }

    override fun onBackPressed() {
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        (fragment as? IOnBackPressed)?.onBackPressed()?.not().let {
            if (it == true) super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _homepageBinding = null
        _navHostFragment = null
    }
}
