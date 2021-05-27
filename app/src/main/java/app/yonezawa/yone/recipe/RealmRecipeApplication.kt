package app.yonezawa.yone.recipe

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmRecipeApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        //Realmの初期化
        Realm.init(this)
        val realmConfig: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }


}