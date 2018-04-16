package org.cookpad.rxbroadcaster_app_test.home.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_recipe.view.*
import org.cookpad.rxbroadcaster_app_test.R
import org.cookpad.rxbroadcaster_app_test.data.models.Recipe

class RecipeAdapter(val detailClicks: (Recipe) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var recipes = emptyList<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recipe, parent, false)
            .run {
                object : RecyclerView.ViewHolder(this) {}
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            recipes[position].apply {
                tvTitle.text = name
                tvDescription.text = description

                val drawableBookmark = if (bookmarked) R.drawable.ic_bookmarked else R.drawable.ic_bookmark
                ivBookmarkButton.setImageResource(drawableBookmark)

                val (drawable, colorFilter) = if (liked) {
                    R.drawable.ic_liked to ContextCompat.getColor(context, R.color.likedColor)
                } else {
                    R.drawable.ic_like to ContextCompat.getColor(context, R.color.textColor)
                }

                ivLikeButton.setImageResource(drawable)
                ivLikeButton.setColorFilter(colorFilter)

                rlRoot.setOnClickListener { detailClicks(this) }
            }
        }
    }

    fun setAll(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }

    override fun getItemCount() = recipes.size
}