package app.yonezawa.yone.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm


class DisplayActivity : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val recipeid = intent.getStringExtra("data")
        val save = realm.where(Recipe::class.java).equalTo("id",recipeid).findFirst()

    }

}