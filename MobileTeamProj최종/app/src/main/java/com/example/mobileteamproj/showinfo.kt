package com.example.mobileteamproj

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class showinfo : AppCompatActivity() {

    private lateinit var foodNameText: TextView
    private lateinit var foodNumText: TextView
    private lateinit var eatTimeText: TextView
    private lateinit var foodKcalText: TextView
    private lateinit var foodCommentText: TextView
    private lateinit var eatPlaceText: TextView
    private lateinit var foodImage: ImageView

    private lateinit var toolbar: Toolbar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showinfo)

        toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }
            val intent=intent
            val food=intent.getParcelableExtra("parcel") as Food?

            foodNameText=findViewById(R.id.foodNametext)
            foodNumText=findViewById(R.id.foodNumtext)
            eatTimeText=findViewById(R.id.eatTimetext)
            foodKcalText=findViewById(R.id.foodKcaltext)
            foodCommentText=findViewById(R.id.foodCommenttext)
            eatPlaceText=findViewById(R.id.eatPlacetext)
            foodImage=findViewById(R.id.foodImageview)

            foodNameText.text="음식 이름 : " + food?.foodName
            foodNumText.text="음식 수량 : " + food?.foodNum.toString()
            eatTimeText.text="식사 시간 : " + food?.eatTime
            foodKcalText.text="칼로리 : " + food?.foodKcal.toString()
            foodCommentText.text="코멘트 : " + food?.foodComment
            eatPlaceText.text="식사 장소 : " + food?.eatPlace
            foodImage.setImageBitmap(food?.image)

    }
}