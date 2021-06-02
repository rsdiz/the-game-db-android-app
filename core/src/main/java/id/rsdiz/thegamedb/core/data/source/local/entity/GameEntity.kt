package id.rsdiz.thegamedb.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    @NotNull
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Float,
    val parentPlatforms: String,
    val genres: String,
    val released: String,
    val websiteUrl: String,
    val playtime: Int,
    val platforms: String,
    val developers: String,
    val description: String,
    var isFavorite: Boolean
)
