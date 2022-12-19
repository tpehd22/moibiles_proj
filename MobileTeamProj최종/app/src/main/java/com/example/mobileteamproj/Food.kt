package com.example.mobileteamproj

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "food")
@Parcelize
data class Food(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "image") var image: Bitmap? = null,
    @ColumnInfo(name = "eatplace") var eatPlace: String? = null,
    @ColumnInfo(name = "foodname") var foodName: String? = null,
    @ColumnInfo(name = "foodnum") var foodNum: Int? = null,
    @ColumnInfo(name = "eattime") var eatTime: String? = null,
    @ColumnInfo(name = "foodkcal") var foodKcal: Int? = null,
    @ColumnInfo(name = "foodcomment") var foodComment: String? = null,
): Parcelable