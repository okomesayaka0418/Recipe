package app.yonezawa.yone.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_recipe_data_cell.view.*

 open class RecyclerViewAdapter(
         private  val context: Context,
         private  var recipeList: OrderedRealmCollection<Recipe>?,
         private  val autoUpdate: Boolean):

        RealmRecyclerViewAdapter<Recipe, RecyclerViewAdapter.RecipeViewHolder>(recipeList,autoUpdate) {
     private lateinit var listener: OnItemClickListener//リスナ変数定義

     //インターフェ～ス作成
     interface OnItemClickListener {
         fun onItemClick(view: View, position: Int, recipe: Recipe)

     }
     //リスナーをセット
     fun setOnItemClickListener(listener: OnItemClickListener){
         this.listener = listener

     }


     override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecipeViewHolder {
         val view = LayoutInflater.from(context).inflate(R.layout.item_recipe_data_cell, viewGroup, false)
         return RecipeViewHolder(view)
     }
     override fun getItemCount(): Int = recipeList?.size ?: 0
     //

     override fun onBindViewHolder(holder:RecipeViewHolder, position: Int) {
         //Realmの1行分を取り出している
         val recipe: Recipe = recipeList?.get(position) ?: return

         //クリックしたときの処理
         holder.itemView.setOnClickListener{
             listener.onItemClick(it, position, recipe)


         }

         holder.menuTextView.text = recipe.menu
         holder.recipeImageView.setImageResource(recipe.imageId)
     }

     class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         val recipeImageView: ImageView = view.findViewById(R.id.recipeImageView)
         val menuTextView: TextView = view.findViewById(R.id.menuTextView)

     }

 }














