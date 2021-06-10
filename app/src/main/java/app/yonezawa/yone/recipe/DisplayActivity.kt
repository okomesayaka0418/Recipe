package app.yonezawa.yone.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_recipe_data_cell.*


class DisplayActivity : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val recipeid = intent.getStringExtra("data")
        val save = realm.where(Recipe::class.java).equalTo("id",recipeid).findFirst()

         displaymenu.text = save?.menu
         diplaymaking.text = save?.making
         displaycomment.text = save?.comment






    }

}