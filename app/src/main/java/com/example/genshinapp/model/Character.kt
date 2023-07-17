package com.example.genshinapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id:Int,
    val name: String,
    val element: String,
    val region: String,
    val picture: String
) : Parcelable
