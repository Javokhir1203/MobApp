package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class RecipeAdapter(private var recipes: List<RecipeItem>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {

    private var filteredRecipes = recipes // Initially, filteredRecipes is the same as recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = filteredRecipes[position]
        holder.bind(recipe, listener)
    }

    override fun getItemCount(): Int = filteredRecipes.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().trim().toLowerCase(Locale.getDefault())
                val filteredList = if (query.isEmpty()) {
                    recipes // Show all recipes if query is empty
                } else {
                    recipes.filter {
                        it.title.toLowerCase(Locale.getDefault()).contains(query) ||
                                it.description.toLowerCase(Locale.getDefault()).contains(query)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredRecipes = results?.values as List<RecipeItem>
                notifyDataSetChanged() // Notify adapter of the changes
            }
        }
    }

    fun updateData(newRecipes: List<RecipeItem>) {
        recipes = newRecipes
        filteredRecipes = newRecipes
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onLikeClick(position: Int)
        fun onShareClick(position: Int)
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.recipe_title)
        private val descriptionTextView: TextView = view.findViewById(R.id.recipe_description)
        private val recipeImageView: ImageView = view.findViewById(R.id.recipe_image)
        private val likeButton: Button = view.findViewById(R.id.like_button)
        private val shareButton: Button = view.findViewById(R.id.share_button)

        fun bind(recipe: RecipeItem, listener: OnItemClickListener) {
            titleTextView.text = recipe.title
            descriptionTextView.text = recipe.description
            recipeImageView.setImageResource(recipe.imageResId)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            likeButton.setOnClickListener {
                listener.onLikeClick(adapterPosition)
            }

            shareButton.setOnClickListener {
                listener.onShareClick(adapterPosition)
            }
        }
    }
}

