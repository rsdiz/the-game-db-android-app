package id.rsdiz.thegamedb.core.data.source.remote.response.games

import com.google.gson.annotations.SerializedName

data class ParentPlatformsResponse(
    @field:SerializedName("platform")
    val platform: PlatformResponse
)
