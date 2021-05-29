package app.yonezawa.yone.recipe

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dataStore: SharedPreferences = getSharedPreferences("DataStore",Context.MODE_PRIVATE)

        val stringText = menueditText.text.toString()


        val recipe: Recipe? = read()
        //データーベースから取得したRecipeをeditTextに表示する
        if (recipe != null) {
            menueditText.setText(recipe.menu)
            makingeditText.setText(recipe.making)
            commenteditText.setText(recipe.comment)

        }
        //それぞれのテキストを取得して、save()というメソッドに引数として渡している
        saveButton.setOnClickListener {
            val menu: String = menueditText.text.toString()
            val making: String = makingeditText.text.toString()
            val comment: String = commenteditText.text.toString()
            save(menu,making,comment)



           /* //入力文字列を”Input”に書き込む
            val editor = dataStore.edit()
            editor.putString("Input",stringText)
            editor.apply()*/
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
        val recipe: Recipe? = read()
        realm.executeTransaction{
            if (recipe != null) {
                //レシピの更新
                recipe.menu = menu
                recipe.making = making
                recipe.comment = comment
            } else {
                //レシピの新規作成
               val newRecipe: Recipe = it.createObject(Recipe::class.java)
                newRecipe.menu = menu
                newRecipe.making = making
                newRecipe.comment = comment
            }
            Snackbar.make(container,"保存しました！！",Snackbar.LENGTH_SHORT).show()
        }

    }
}
