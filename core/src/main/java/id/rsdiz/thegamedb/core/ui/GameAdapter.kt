package id.rsdiz.thegamedb.core.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rsdiz.thegamedb.core.R
import id.rsdiz.thegamedb.core.databinding.ItemGameListBinding
import id.rsdiz.thegamedb.core.domain.model.Game
import id.rsdiz.thegamedb.core.utils.FormatPattern
import id.rsdiz.thegamedb.core.utils.format
import id.rsdiz.thegamedb.core.utils.toDate

class GameAdapter : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    private var games: MutableList<Game> = mutableListOf()
    private var onItemClick: ((Game) -> Unit)? = null

    fun setGames(games: List<Game>) {
        with(this.games) {
            clear()
            addAll(games)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((Game) -> Unit)) {
        onItemClick = listener
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
                game.released?.let {
                    val date = it.toDate(FormatPattern.ISO_DATE)
                    val dateLocal = date?.format(FormatPattern.LOCAL_DATE)
                    gameReleaseDate.text =
                        root.context.getString(R.string.release_date_value, dateLocal)
                }
                game.backgroundImage?.let {
                    Glide.with(root.context)
                        .load(Uri.parse(it))
                        .apply(RequestOptions.placeholderOf(R.drawable.bg_image_loading))
                        .error(R.drawable.bg_image_error)
                        .centerCrop()
                        .into(gameImage)
                }
                bindGenre(game.genres.split(", ").toTypedArray())
                bindPlatforms(game.parentPlatforms.split(", ").toTypedArray())
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

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(games[bindingAdapterPosition])
            }
        }
    }
}
