package id.rsdiz.thegamedb.mobile.favorite

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.rsdiz.thegamedb.mobile.di.FavoriteModule

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {
    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModule: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}
