package app.yonezawa.yone.recipe

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class MainActivity: AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataStore: SharedPreferences = getSharedPreferences("DataStore",Context.MODE_PRIVATE)

        val stringText = menueditText.text.toString()


        val recipe: Recipe? = read()

        //それぞれのテキストを取得して、save()というメソッドに引数として渡している
        saveButton.setOnClickListener {
            val menu: String = menueditText.text.toString()
            val making: String = makingeditText.text.toString()
            val comment: String = commenteditText.text.toString()
            save(menu,making,comment)

        }
        //保存ボタン押したらSearchActivityに画面遷移
        saveButton.setOnClickListener {
            val toSearchActivityIntent = Intent(this, SearchActivity::class.java)
            startActivity(toSearchActivityIntent)
        }



    }
    //画面が終了したときに表示される
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    //画面が起動したときにすでに保存されているRecipeデータを取得する
    fun read (): Recipe? {
        return realm.where(Recipe::class.java).findFirst()
    }
    //saveメッソド内でそれぞれのコメントを受け取り保存する
    fun save(menu: String, making: String,comment: String) {
        //  val recipe: Recipe? = read()
        realm.executeTransaction {
            /* if (recipe != null) {
                //レシピの更新
                recipe.menu = menu
                recipe.making = making
                recipe.comment = comment
            } else {*/
            //レシピの新規作成
            val newRecipe: Recipe =
                it.createObject(Recipe::class.java, UUID.randomUUID().toString())
            newRecipe.menu = menu
            newRecipe.making = making
            newRecipe.comment = comment
        }
        Snackbar.make(container, "保存しました！！", Snackbar.LENGTH_SHORT).show()




    }
    }

