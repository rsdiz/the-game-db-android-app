package id.rsdiz.thegamedb.core.domain.usecase.developers

import id.rsdiz.thegamedb.core.data.Resource
import id.rsdiz.thegamedb.core.domain.model.Developers
import id.rsdiz.thegamedb.core.domain.repository.IDevelopersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DevelopersUseCase @Inject constructor(private val repository: IDevelopersRepository) :
    IDevelopersUseCase {
    override fun getDevelopers(): Flow<Resource<List<Developers>>> = repository.getDevelopers()

    override fun getDetailDevelopers(id: Int): Flow<Resource<Developers>> =
        repository.getDetailDevelopers(id)
}
