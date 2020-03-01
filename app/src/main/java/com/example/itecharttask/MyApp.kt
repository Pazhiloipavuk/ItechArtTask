package com.example.itecharttask

import android.app.Application
import com.example.itecharttask.taskStorage.Storage
import io.realm.Realm
import io.realm.RealmConfiguration
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class MyApp : Application() {

    companion object {
        lateinit var kodein: Kodein
    }

    override fun onCreate() {
        super.onCreate()

        kodein = Kodein {
            bind<Storage>() with singleton {
                Storage()
            }
        }

        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}