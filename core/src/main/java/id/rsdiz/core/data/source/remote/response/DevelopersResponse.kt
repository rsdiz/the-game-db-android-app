package id.rsdiz.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DevelopersResponse(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("slug")
    val slug: String
)
