package id.rsdiz.thegamedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description_raw")
    val description: String?,
    @field:SerializedName("released")
    val released: String,
    @field:SerializedName("background_image")
    val backgroundImage: String?,
    @field:SerializedName("website")
    val websiteUrl: String?,
    @field:SerializedName("rating")
    val rating: Float,
    @field:SerializedName("playtime")
    val playtime: Int,
    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformsResponse>,
    @field:SerializedName("platforms")
    val platforms: List<PlatformsResponse>,
    @field:SerializedName("developers")
    val developers: List<DevelopersResponse>,
    @field:SerializedName("genres")
    val genres: List<GenreResponse>
)
