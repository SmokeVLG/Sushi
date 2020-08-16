package com.maxden.sushi.network

import com.maxden.sushi.database.*
import com.maxden.sushi.model.AccountModel
import com.maxden.sushi.model.CartModel
import com.maxden.sushi.model.ItemTypeModel
import com.maxden.sushi.model.KitTypeModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class KitType(
    val id: String,
    var name: String,
    @Json(name="image") val imgSrcUrl: String,
    val description: String,
    val price: String
)

@JsonClass(generateAdapter = true)
data class ItemType (
    val id: String,
    @Json(name= "item_name") val name: String,
    @Json(name = "item_image") val imgSrcUrl: String,
    val price: String
)

//for database to model(domain)
fun List<DatabaseKitType>.asDomainKitTypeModel(): List<KitTypeModel> {
    return map {
        KitTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            description = it.description,
            price = it.price
        )
    }
}
fun List<DatabaseItemType1>.asDomainItemType1Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<DatabaseItemType2>.asDomainItemType2Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}
fun List<DatabaseItemType3>.asDomainItemType3Model(): List<ItemTypeModel> {
    return map {
        ItemTypeModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//FOR CART::
fun DatabaseKitType.asDatabaseCartType(): DatabaseCart{
    return DatabaseCart(
        id = id,
        name = name,
        imgSrcUrl = imgSrcUrl,
        price = price
    )
}
fun List<DatabaseCart>.asDomainCartModel(): List<CartModel>{
    return map {
        CartModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl,
            price = it.price
        )
    }
}

//For Account
fun List<DatabaseAccount>.asDomainAccountModel(): List<AccountModel>{
    return map {
        AccountModel(
            id = it.id,
            price = it.price
        )
    }
}