package id.rsdiz.thegamedb.mobile.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import id.rsdiz.thegamedb.mobile.R
import id.rsdiz.thegamedb.mobile.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private var _homepageBinding: ActivityHomepageBinding? = null
    private val binding get() = _homepageBinding as ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _homepageBinding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_homepage)
            as NavHostFragment
        NavigationUI.setupWithNavController(
            binding.bottomNav,
            navHostFragment.navController
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _homepageBinding = null
    }
}
