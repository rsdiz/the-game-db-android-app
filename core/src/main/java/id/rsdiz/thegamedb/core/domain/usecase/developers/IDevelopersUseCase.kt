package id.rsdiz.thegamedb.core.domain.usecase.developers

import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import kotlinx.coroutines.flow.Flow

interface IDevelopersUseCase {
    fun getDevelopers(): Flow<Resource<List<Developers>>>

    fun getDetailDevelopers(id: Int): Flow<Resource<Developers>>
}
