package app.yonezawa.yone.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (private val context: Context):
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    val items: MutableList<RecipeData> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
                LayoutInflater.from(context).inflate(R.layout.item_recipe_data_cell, parent, false)
        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return items.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.recipeImage.setImageResource(item.recipeImageResource)
        holder.menuTextView.text = item.menu

    }
    fun addAll(item: List<RecipeData>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipeImageView)
        val menuTextView: TextView = view.findViewById(R.id.menuTextView)
    }


}