package id.rsdiz.thegamedb.mobile.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.ui.PlatformAdapter
import id.rsdiz.thegamedb.core.utils.FormatPattern
import id.rsdiz.thegamedb.core.utils.format
import id.rsdiz.thegamedb.core.utils.toDate
import id.rsdiz.thegamedb.mobile.R
import id.rsdiz.thegamedb.mobile.databinding.ActivityDetailBinding

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _detailBinding: ActivityDetailBinding? = null
    private val binding get() = _detailBinding as ActivityDetailBinding

    private val args: DetailActivityArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    private var data: Game? = null
    private var favoriteMenu: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val gameId = args.gameId

        if (gameId != 0) {
            detailViewModel.getDetailGame(gameId).observe(this) { resource ->
                when (resource) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        data = resource.data as Game
                        data?.let { game ->
                            setFavoriteState(game.isFavorite)
                            populateData(game)
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
        }
    }

    private fun populateData(game: Game) {
        with(binding) {
            supportActionBar?.subtitle = game.name
            game.backgroundImage?.let {
                Glide.with(root.context)
                    .load(Uri.parse(it))
                    .apply(RequestOptions.placeholderOf(R.drawable.bg_image_loading))
                    .error(R.drawable.bg_image_error)
                    .centerCrop()
                    .into(gameImage)
            }
            populateParentPlatforms(game.parentPlatforms.split(", ").toTypedArray())
            gameAveragePlaytime.text =
                getString(R.string.average_playtime, game.playtime)
            gameDescription.text = game.description
            gamePlatform.text = game.platforms
            val date = game.released?.toDate(FormatPattern.ISO_DATE)
            val dateLocal = date?.format(FormatPattern.LOCAL_DATE)
            gameReleaseDate.text = dateLocal ?: getString(R.string.not_yet_available)
            val rating = String.format("%.2f", game.rating).toFloat()
            gameRatingBar.rating = rating
            gameRatingNum.text = getString(R.string.rating_value, rating.toString())
            gameDevelopers.text = game.developers
            buttonVisitWeb.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(game.websiteUrl)
                    startActivity(this)
                }
            }
        }
    }

    private fun populateParentPlatforms(parentPlatforms: Array<String>) {
        val platformAdapter = PlatformAdapter()
        platformAdapter.setPlatforms(parentPlatforms)
        binding.gameParentPlatform.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = platformAdapter
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        favoriteMenu?.let {
            if (it.itemId == R.id.menu_favorite) {
                if (isFavorite) {
                    it.setIcon(R.drawable.ic_heart_fill)
                    it.title = getString(R.string.remove_from_favorite)
                } else {
                    it.setIcon(R.drawable.ic_heart_outline)
                    it.title = getString(R.string.add_to_favorite)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) =
        with(binding) {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_appbar_menu, menu)
        favoriteMenu = menu?.findItem(R.id.menu_favorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                data?.let {
                    detailViewModel.setFavoriteGame(it)
                    setFavoriteState(it.isFavorite)
                }
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _detailBinding = null
    }
}
