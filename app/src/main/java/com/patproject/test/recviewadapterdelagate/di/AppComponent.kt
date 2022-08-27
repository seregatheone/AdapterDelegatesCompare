package com.patproject.test.recviewadapterdelagate.di

import android.app.Application
import android.content.Context
import com.patproject.test.api.DataService
import com.patproject.test.api.RetrofitBuilder
import com.patproject.test.recviewadapterdelagate.ui.TestFragment
import com.patproject.test.recviewadapterdelagate.ui.TestFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class AppScope


@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    fun inject(testFragment: TestFragment)

    val testFragmentViewModelFactory : TestFragmentViewModel.Companion.TestFragmentViewModelFactory

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder

        fun build():AppComponent
    }
}

@Module
class AppModule{
    @Provides
    @AppScope
    fun provideDataService(): DataService = RetrofitBuilder.dataService()
}
