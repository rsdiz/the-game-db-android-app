package id.rsdiz.thegamedb.core.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.rsdiz.thegamedb.core.R
import id.rsdiz.thegamedb.core.databinding.ItemPlatformListBinding
import id.rsdiz.thegamedb.core.utils.Const

class PlatformAdapter : RecyclerView.Adapter<PlatformAdapter.ViewHolder>() {
    private var platforms: Array<String> = arrayOf()

    fun setPlatforms(platforms: Array<String>) {
        this.platforms = platforms
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformAdapter.ViewHolder =
        ViewHolder(
            ItemPlatformListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(platforms[position])

    override fun getItemCount(): Int = platforms.size

    inner class ViewHolder(private val binding: ItemPlatformListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(platform: String) {
            binding.apply {
                val drawable = when (platform.lowercase()) {
                    Const.PC -> R.drawable.ic_windows
                    Const.XBOX -> R.drawable.ic_xbox
                    Const.PLAYSTATION -> R.drawable.ic_playstation
                    Const.NINTENDO -> R.drawable.ic_nintendo
                    Const.ANDROID -> R.drawable.ic_android
                    Const.IOS -> R.drawable.ic_ios
                    Const.LINUX -> R.drawable.ic_linux
                    Const.MAC -> R.drawable.ic_mac
                    else -> null
                }
                if (drawable != null) {
                    platformImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            platformImage.context,
                            drawable
                        )
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        platformImage.tooltipText = platform
                    }
                } else {
                    root.visibility = View.GONE
                }
            }
        }
    }
}
