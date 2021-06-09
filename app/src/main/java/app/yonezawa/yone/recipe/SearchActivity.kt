package app.yonezawa.yone.recipe

import android.content.Intent
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class SearchActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val recipeList = readAll()


        val intent: Intent = Intent(this,DisplayActivity::class.java)



        val adapter = RecyclerViewAdapter(this, recipeList, true)
        adapter.setOnItemClickListener(
            object :RecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int, recipe: Recipe) {
                    //レシピデータ渡す処理
                    intent.putExtra("data", recipe.id)
                    startActivity(intent)


                }


            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

       //adapter.addAll(Recipe)



        //intentButtonを押したら、MainActivityへ移動
        floatingActionButton.setOnClickListener {
            val toMainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainActivityIntent)

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    fun readAll(): RealmResults<Recipe>{
        return realm.where(Recipe::class.java).findAll()

    }

    fun create(imageId: Int, menu: String) {
        realm.executeTransaction {
            val recipe = it.createObject(Recipe::class.java, UUID.randomUUID().toString())
            recipe.imageId = imageId
            recipe.menu = menu
        }
    }




}