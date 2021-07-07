package app.yonezawa.yone.recipe

import android.content.Intent
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
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


        //RecyclerViewに線をいれる
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecoration)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //スワイプして削除
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE,ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewHolder?.let {
                    val id = adapter.getItem(viewHolder.adapterPosition)?.id ?: return@let
                    val target = realm.where(Recipe::class.java).equalTo("id",id).findFirst()
                    realm.executeTransaction{
                        target?.deleteFromRealm()
                    }
                }
            }
         })
        itemTouchHelper.attachToRecyclerView(recyclerView)






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