package id.rsdiz.thegamedb.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "developers")
data class DevelopersEntity(
    @PrimaryKey
    @NotNull
    val id: Int,
    val name: String,
    @NotNull
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String?,
    val description: String?
)
