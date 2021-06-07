package id.rsdiz.thegamedb.mobile.developer

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.rsdiz.thegamedb.mobile.di.DeveloperModule

@Component(dependencies = [DeveloperModule::class])
interface DeveloperComponent {
    fun inject(developersFragment: DevelopersFragment)
    fun inject(detailDevelopersActivity: DetailDevelopersActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(developerModule: DeveloperModule): Builder
        fun build(): DeveloperComponent
    }
}
