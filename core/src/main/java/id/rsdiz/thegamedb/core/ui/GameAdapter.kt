package id.rsdiz.thegamedb.core.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rsdiz.thegamedb.core.R
import id.rsdiz.thegamedb.core.databinding.ItemGameListBinding
import id.rsdiz.thegamedb.core.domain.model.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    private var games: MutableList<Game> = mutableListOf()

    fun setGames(games: List<Game>) {
        with(this.games) {
            clear()
            addAll(games)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ViewHolder =
        ViewHolder(
            ItemGameListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(games[position])

    override fun getItemCount(): Int = games.size

    inner class ViewHolder(private val binding: ItemGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            with(binding) {
                gameTitle.text = game.name
                gameReleaseDate.text =
                    StringBuilder(root.context.getString(R.string.release_date)).append(
                        game.released
                    )
                Glide.with(root.context)
                    .load(Uri.parse(game.backgroundImage))
                    .centerCrop()
                    .into(gameImage)
                bindGenre(game.genres.split(',').toTypedArray())
                bindPlatforms(game.parentPlatforms.split(',').toTypedArray())
            }
        }

        private fun bindGenre(genres: Array<String>) {
            val genreAdapter = GenreAdapter()
            genreAdapter.setGenres(genres)
            binding.rvGenres.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = genreAdapter
            }
        }

        private fun bindPlatforms(platforms: Array<String>) {
            val platformAdapter = PlatformAdapter()
            platformAdapter.setPlatforms(platforms)
            binding.rvPlatforms.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = platformAdapter
            }
        }
    }
}
