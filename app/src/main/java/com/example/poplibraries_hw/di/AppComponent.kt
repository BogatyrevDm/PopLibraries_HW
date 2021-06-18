package com.example.poplibraries_hw.di

import android.content.Context
import com.example.poplibraries_hw.di.modules.*
import com.example.poplibraries_hw.ui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.reactivex.rxjava3.core.Scheduler
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        UserUiModule::class,
        ApiModule::class,
        CacheModule::class,
        RepoModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatoreHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withScheduler(scheduler: Scheduler): Builder

        fun build():AppComponent
    }
}