package id.rsdiz.thegamedb.core.data.source.remote.response.games

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String
)
