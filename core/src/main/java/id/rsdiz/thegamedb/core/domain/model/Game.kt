package id.rsdiz.thegamedb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Float,
    val parentPlatforms: String,
    val genres: String,
    val released: String,
    val websiteUrl: String,
    val playtime: Int,
    val platforms: String,
    val developers: String,
    val description: String,
    val isFavorite: Boolean
) : Parcelable
