package id.rsdiz.thegamedb.core.data.source.remote.response.developer

import com.google.gson.annotations.SerializedName

data class ListDevelopersResponse(
    @field:SerializedName("results")
    val results: List<DeveloperResponse>
)
