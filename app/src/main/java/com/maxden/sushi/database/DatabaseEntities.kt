package com.maxden.sushi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entities
//For ItemType
@Entity
data class DatabaseItemType3 constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val imgSrcUrl: String)

