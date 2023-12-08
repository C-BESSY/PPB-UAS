package com.ppb1.uasppb

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ppb1.uasppb.ui.DetailActivity
import com.ppb1.uasppb.ui.ID_MEAL
import org.json.JSONArray

class MealAdapter(private val mealList: JSONArray) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList.getJSONObject(position)
        val mealName = meal.getString("strMeal")
        val mealImage = meal.getString("strMealThumb")
        val mealId = meal.getString("idMeal")

        holder.bind(mealName, mealImage, mealId)
    }

    override fun getItemCount(): Int {
        return mealList.length()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMeal = view.findViewById<TextView>(R.id.tv_meal)
        val imMeal = view.findViewById<ImageView>(R.id.im_meal)
        val layout = view.findViewById<ConstraintLayout>(R.id.container)

        fun bind(mealName: String, mealImage: String, mealId: String) {
            tvMeal.text=mealName
            Glide.with(imMeal.context).load(mealImage).into(imMeal)
            layout.setOnClickListener{
                val intent = Intent(layout.context, DetailActivity::class.java)
                intent.putExtra(ID_MEAL, mealId)
                layout.context.startActivity(intent)

            }
        }
    }
}
