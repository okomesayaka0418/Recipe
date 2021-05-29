package app.yonezawa.yone.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    val recipeData:List<RecipeData> = listOf(
            RecipeData(R.drawable.ic_launcher_background,"カレー"),
            RecipeData(R.drawable.ic_launcher_background,"ピーマンの肉詰め"),
            RecipeData(R.drawable.ic_launcher_background,"肉じゃが"),
            RecipeData(R.drawable.ic_launcher_background,"生ハムアボカド"),
            RecipeData(R.drawable.ic_launcher_background,"やみつきキャベツ"),
            RecipeData(R.drawable.ic_launcher_background,"レンコンの肉ばさみ"),
            RecipeData(R.drawable.ic_launcher_background,"切り干し大根"),
            RecipeData(R.drawable.ic_launcher_background,"ふわとろオムレツ"),
            RecipeData(R.drawable.ic_launcher_background,"餃子"),
            RecipeData(R.drawable.ic_launcher_background,"えびまよ"),

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val adapter = RecyclerViewAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.addAll(recipeData)

        //intentButtonを押したら、MainActivityへ移動
        floatingActionButton.setOnClickListener {
            val toMainActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(toMainActivityIntent)


        }
    }


}