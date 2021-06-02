package app.yonezawa.yone.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_recipe_data_cell.view.*

 open class RecyclerViewAdapter(
        private  val context: Context,
        private  var recipeList: OrderedRealmCollection<Recipe>?,
        private  val autoUpdate: Boolean):

        RealmRecyclerViewAdapter<Recipe, RecyclerViewAdapter.RecipeViewHolder>(recipeList,autoUpdate) {


     override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecipeViewHolder {
         val view = LayoutInflater.from(context).inflate(R.layout.item_recipe_data_cell, viewGroup, false)
         return RecipeViewHolder(view)
     }
     override fun getItemCount(): Int = recipeList?.size ?: 0

     override fun onBindViewHolder(holder:RecipeViewHolder, position: Int) {
         val recipe: Recipe = recipeList?.get(position) ?: return

         holder.menuTextView.text = recipe.menu
         holder.recipeImageView.setImageResource(recipe.imageId)
     }

     class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val recipeImageView: ImageView = view.recipeImageView
         val menuTextView: TextView = view.menuTextView

     }
 }














