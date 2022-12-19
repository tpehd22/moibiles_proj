package com.example.mobileteamproj.adpter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileteamproj.Food
import com.example.mobileteamproj.R


class FoodListAdapter : ListAdapter<Food, FoodListAdapter.ViewHolder>(
    FoodDiffCallback()
) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { listener?.onItemClick(item) }
    }

    class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(food: Food) {
            val foodNameText = itemView.findViewById<TextView>(R.id.food_name_text)
            val foodNumText = itemView.findViewById<TextView>(R.id.food_num_text)
            val foodCommentText = itemView.findViewById<TextView>(R.id.food_comment_text)
            val eatTimeText = itemView.findViewById<TextView>(R.id.eat_time_text)
            val eatPlaceText = itemView.findViewById<TextView>(R.id.eat_place_text)
            val foodKcalText = itemView.findViewById<TextView>(R.id.food_kcal_text)
           // val foodImage=(ImageView)findViewById(R.id.food_image_view);

            foodNameText.text = "음식 이름 : " + food.foodName
            foodNumText.text = "음식 수량 : " + food.foodNum.toString()
            foodCommentText.text ="코멘트 : " + food.foodComment
            eatTimeText.text="식사 시간 : " + food.eatTime
            eatPlaceText.text="식사 장소 : " + food.eatPlace
            foodKcalText.text="칼로리 : " + food.foodKcal.toString()
           // foodImage.ImageView.setImageBitmap(food.image)



        }
    }

    interface Listener {
        fun onItemClick(food: Food?)
    }
}


class FoodDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}