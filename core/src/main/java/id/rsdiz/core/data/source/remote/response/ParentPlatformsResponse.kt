package id.rsdiz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ParentPlatformsResponse(
    @field:SerializedName("platform")
    val platform: List<PlatformResponse>
)
