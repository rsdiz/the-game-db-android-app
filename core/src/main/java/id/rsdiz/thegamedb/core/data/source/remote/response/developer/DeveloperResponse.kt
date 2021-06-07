package id.rsdiz.thegamedb.core.data.source.remote.response.developer

import com.google.gson.annotations.SerializedName

data class DeveloperResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String,
    @field:SerializedName("games_count")
    val gamesCount: Int,
    @field:SerializedName("image_background")
    val imageBackground: String?,
    @field:SerializedName("description")
    val description: String?
)
