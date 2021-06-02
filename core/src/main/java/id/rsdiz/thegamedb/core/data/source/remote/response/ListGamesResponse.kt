package id.rsdiz.thegamedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGamesResponse(
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("next")
    val next: String?,
    @field:SerializedName("previous")
    val previous: String?,
    @field:SerializedName("results")
    val results: List<GameResponse>
)
