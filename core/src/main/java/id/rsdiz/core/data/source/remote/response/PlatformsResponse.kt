package id.rsdiz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlatformsResponse(
    @field:SerializedName("platform")
    val platform: List<PlatformResponse>
)

data class PlatformResponse(
    @field:SerializedName("name")
    val name: String
)
