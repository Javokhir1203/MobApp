package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable
import java.util.Locale

class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var searchInput: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Sample data for recipes with descriptions
        val recipes = listOf(
            RecipeItem(
                id = 1,
                title = "Margherita Pizza",
                imageResId = R.drawable.sample_recipe_image,
                description = "A classic Margherita pizza with a crispy crust, fresh tomato sauce, melted mozzarella, and fragrant basil."
            ),
            RecipeItem(
                id = 2,
                title = "Cheeseburger",
                imageResId = R.drawable.sample_recipe_image,
                description = "A juicy cheeseburger with a grilled beef patty, melted cheddar, lettuce, tomato, and a soft sesame seed bun."
            ),
            RecipeItem(
                id = 3,
                title = "Spaghetti Carbonara",
                imageResId = R.drawable.sample_recipe_image,
                description = "Creamy spaghetti carbonara with crispy pancetta, parmesan, and a velvety egg-based sauce."
            ),
            RecipeItem(
                id = 4,
                title = "Tacos",
                imageResId = R.drawable.sample_recipe_image,
                description = "Soft tacos filled with seasoned beef, fresh lettuce, cheese, sour cream, and salsa."
            ),
            RecipeItem(
                id = 5,
                title = "Sushi",
                imageResId = R.drawable.sample_recipe_image,
                description = "Fresh sushi rolls with vinegared rice, seaweed, and a variety of fillings like tuna, avocado, and cucumber."
            ),
            RecipeItem(
                id = 6,
                title = "Grilled Cheese Sandwich",
                imageResId = R.drawable.sample_recipe_image,
                description = "Crispy golden-brown bread filled with melted cheddar cheese."
            ),
            RecipeItem(
                id = 7,
                title = "Caesar Salad",
                imageResId = R.drawable.sample_recipe_image,
                description = "Crisp romaine lettuce tossed with creamy Caesar dressing, croutons, and parmesan cheese."
            ),
            RecipeItem(
                id = 8,
                title = "Vegetable Stir Fry",
                imageResId = R.drawable.sample_recipe_image,
                description = "A mix of fresh vegetables stir-fried in a savory soy-based sauce, served over rice."
            ),
            RecipeItem(
                id = 9,
                title = "Chicken Alfredo",
                imageResId = R.drawable.sample_recipe_image,
                description = "A creamy pasta dish with grilled chicken, fettuccine noodles, and a rich Alfredo sauce."
            ),
            RecipeItem(
                id = 10,
                title = "Pancakes",
                imageResId = R.drawable.sample_recipe_image,
                description = "Fluffy pancakes served with syrup, butter, and a sprinkle of powdered sugar."
            ),
            RecipeItem(
                id = 11,
                title = "Beef Stew",
                imageResId = R.drawable.sample_recipe_image,
                description = "A hearty stew made with tender beef, carrots, potatoes, and a savory broth."
            ),
            RecipeItem(
                id = 12,
                title = "Falafel",
                imageResId = R.drawable.sample_recipe_image,
                description = "Crispy fried chickpea balls served in pita bread with fresh vegetables and tahini sauce."
            ),
            RecipeItem(
                id = 13,
                title = "Pad Thai",
                imageResId = R.drawable.sample_recipe_image,
                description = "A popular Thai stir-fried noodle dish with shrimp, peanuts, bean sprouts, and lime."
            ),
            RecipeItem(
                id = 14,
                title = "Lobster Roll",
                imageResId = R.drawable.sample_recipe_image,
                description = "Chunks of lobster meat served in a buttered, toasted roll, often with mayo and lemon."
            ),
            RecipeItem(
                id = 15,
                title = "Chicken Parmesan",
                imageResId = R.drawable.sample_recipe_image,
                description = "Breaded and fried chicken cutlets topped with marinara sauce and melted mozzarella cheese."
            )
        )


        recyclerView = view.findViewById(R.id.recycler_view)
        searchInput = view.findViewById(R.id.search_input)

        // Initialize the adapter
        recipeAdapter = RecipeAdapter(recipes, object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = recipes[position]
                Toast.makeText(context, "Clicked on: ${item.title}", Toast.LENGTH_SHORT).show()
            }

            override fun onLikeClick(position: Int) {
                val item = recipes[position]
                Toast.makeText(context, "Liked: ${item.title}", Toast.LENGTH_SHORT).show()
            }

            override fun onShareClick(position: Int) {
                val item = recipes[position]
                Toast.makeText(context, "Shared: ${item.title}", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recipeAdapter

        // Add text watcher to search input
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {

                val query = editable.toString().trim().toLowerCase(Locale.getDefault())

                if (query.length >= 3) {
                    recipeAdapter.filter.filter(query)
                } else {

                    recipeAdapter.filter.filter("")
                }
            }
        })
    }
}

