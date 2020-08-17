package com.maxden.sushi.network

import com.maxden.sushi.database.*
import com.maxden.sushi.model.ItemTypeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ItemType (
    val id: String,
    @Json(name= "item_name") val name: String,
    @Json(name = "item_image") val imgSrcUrl: String,
    val price: String
)

fun List<DatabaseItemType3>.asDomainItemType3Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl
        )
    }
}

