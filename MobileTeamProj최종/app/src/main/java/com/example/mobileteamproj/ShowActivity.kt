package com.example.mobileteamproj

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileteamproj.adpter.FoodListAdapter
import java.io.ByteArrayOutputStream

class ShowActivity : AppCompatActivity() {
    private lateinit var dateTextField: AppCompatEditText
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var searchButton: AppCompatButton
    private lateinit var foodImageView: ImageView
    private lateinit var closeButton: ImageButton
    private lateinit var kcalText: TextView
    private lateinit var imageLayout: ConstraintLayout
    private lateinit var toolbar: Toolbar

    private val foodListAdapter = FoodListAdapter().apply {
        listener = object : FoodListAdapter.Listener {
            override fun onItemClick(food: Food?) {

                if (food?.image == null) {
                    Toast.makeText(this@ShowActivity, "이미지가 없습니다.", Toast.LENGTH_SHORT).show()
                } else {


                    val intent=Intent(this@ShowActivity,showinfo::class.java)
                    intent.putExtra("parcel",food)
                    startActivity(intent)

                    Log.d("kkkk",food.toString())


//                    foodImageView.setImageBitmap(food.image)
//                    foodImageView.visibility = View.VISIBLE
                }
            }
        }
    }

    private var foodDb: FoodDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        foodDb = FoodDB.getInstance(this)

        dateTextField = findViewById(R.id.date_text_field)

        foodRecyclerView = findViewById<RecyclerView?>(R.id.food_recycler_view).apply {
            adapter = foodListAdapter
        }

        searchButton = findViewById(R.id.search_button)
        kcalText = findViewById(R.id.kcal_text)
        foodImageView = findViewById(R.id.food_image_view)
        closeButton = findViewById(R.id.close_button)
        imageLayout = findViewById(R.id.image_layout)

        closeButton.setOnClickListener {
            imageLayout.visibility = View.GONE
        }

        searchButton.setOnClickListener {
            val date = dateTextField.text.toString()

            val r = Runnable {
                val foods = if (date.length == 7) {
                    val month = date.substring(0, 7)
                    foodDb?.foodDao?.getAll()?.filter {
                        it.eatTime?.substring(0, 7) == month
                    }
                } else {
                    val month = date.substring(0, 10)
                    foodDb?.foodDao?.getAll()?.filter {
                        it.eatTime?.substring(0, 10) == month
                    }
                }

                runOnUiThread {
                    val totalKcal = foods?.sumOf { food -> food.foodKcal ?: 0 }
                    kcalText.text = totalKcal.toString() + "kcal"
                    foodListAdapter.submitList(foods)
                }
            }

            val thread = Thread(r)
            thread.start()
        }
    }
}