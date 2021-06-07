package id.rsdiz.thegamedb.core.domain.repository

import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import kotlinx.coroutines.flow.Flow

interface IDevelopersRepository {
    fun getDevelopers(): Flow<Resource<List<Developers>>>

    fun getDetailDevelopers(id: Int): Flow<Resource<Developers>>
}
