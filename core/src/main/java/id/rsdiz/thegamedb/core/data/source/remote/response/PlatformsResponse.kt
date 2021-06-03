package id.rsdiz.thegamedb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlatformsResponse(
    @field:SerializedName("platform")
    val platform: PlatformResponse
)

data class PlatformResponse(
    @field:SerializedName("name")
    val name: String
)
