package com.patproject.test.recviewadapterdelagate

import android.app.Application
import android.content.Context
import com.patproject.test.recviewadapterdelagate.di.AppComponent
import com.patproject.test.recviewadapterdelagate.di.DaggerAppComponent

class TestProjectApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
val Context.appComponent : AppComponent
    get() = when(this){
        is TestProjectApplication -> appComponent
        else -> this.applicationContext.appComponent
    }