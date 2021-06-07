package id.rsdiz.thegamedb.mobile.developer.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.mobile.developer.R
import id.rsdiz.thegamedb.mobile.developer.databinding.ItemDeveloperListBinding

class DevelopersAdapter : RecyclerView.Adapter<DevelopersAdapter.ViewHolder>() {
    private var developers: MutableList<Developers> = mutableListOf()
    private var onItemClick: ((Developers) -> Unit)? = null

    fun setDevelopers(developers: List<Developers>) {
        with(this.developers) {
            clear()
            addAll(developers)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: ((Developers) -> Unit)) {
        onItemClick = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevelopersAdapter.ViewHolder =
        ViewHolder(
            ItemDeveloperListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DevelopersAdapter.ViewHolder, position: Int) =
        holder.bind(developers[position])

    override fun getItemCount(): Int = developers.size

    inner class ViewHolder(private val binding: ItemDeveloperListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(developers: Developers) {
            with(binding) {
                developersName.text = developers.name
                developersGamesCount.text =
                    root.context.getString(R.string.games_count_value, developers.gamesCount)
                developers.imageBackground?.let {
                    val options = RequestOptions().centerCrop()

                    Glide.with(root.context)
                        .load(Uri.parse(it))
                        .apply(options)
                        .into(developersImage)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(developers[bindingAdapterPosition])
            }
        }
    }
}
