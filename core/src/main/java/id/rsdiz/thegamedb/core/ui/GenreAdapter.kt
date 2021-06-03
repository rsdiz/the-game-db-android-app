package id.rsdiz.thegamedb.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rsdiz.thegamedb.core.databinding.ItemGenreListBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private var genres: Array<String> = arrayOf()

    fun setGenres(genres: Array<String>) {
        this.genres = genres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder =
        ViewHolder(
            ItemGenreListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(genres[position])

    override fun getItemCount(): Int = genres.size

    inner class ViewHolder(private val binding: ItemGenreListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String) =
            with(binding) {
                genreName.text = genre
            }
    }
}
