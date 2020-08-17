package com.maxden.sushi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entities
@Entity
data class User constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String)

