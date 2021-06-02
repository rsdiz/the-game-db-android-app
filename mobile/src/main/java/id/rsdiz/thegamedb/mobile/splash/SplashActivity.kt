package id.rsdiz.thegamedb.mobile.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.mobile.databinding.ActivitySplashBinding
import id.rsdiz.thegamedb.mobile.home.HomepageActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var _splashBinding: ActivitySplashBinding? = null
    private val binding get() = _splashBinding as ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentHomepage = Intent(this, HomepageActivity::class.java)
        lifecycleScope.launch(Dispatchers.IO) {
            delay(TIMEOUT)
            withContext(Dispatchers.Main) {
                startActivity(intentHomepage)
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _splashBinding = null
    }

    companion object {
        private const val TIMEOUT = 2000L
    }
}
