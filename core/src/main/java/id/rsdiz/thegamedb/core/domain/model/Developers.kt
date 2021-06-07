package id.rsdiz.thegamedb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Developers(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String?,
    val description: String?
) : Parcelable
