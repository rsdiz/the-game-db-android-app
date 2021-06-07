package id.rsdiz.thegamedb.core.data.source.remote.response.games

import com.google.gson.annotations.SerializedName

data class DevelopersResponse(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String
)
