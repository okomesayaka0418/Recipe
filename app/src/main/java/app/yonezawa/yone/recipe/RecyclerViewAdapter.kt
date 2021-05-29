package app.yonezawa.yone.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.OrderedRealmCollectionChangeListener
import kotlinx.android.synthetic.main.activity_main.view.*

class RecyclerViewAdapter(private val context: Context,
    /* private var recipe: OrderedRealmCollection<Recipe>?,
     private var listener: AdapterView.OnItemClickListener,
     private val autoUpdate: Boolean*/
):


   RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    //RecyclerViewに表示するリストを宣言
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
    //position番号の要素をViewHolderに表示
        val item = items[position]
        holder.recipeImage.setImageResource(item.recipeImageResource)
        holder.menuTextView.text = item.menu



    }//adapterのデータ登録
    fun addAll(items: List<RecipeData>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipeImageView)
        val menuTextView: TextView = view.findViewById(R.id.menuTextView)
    }


}