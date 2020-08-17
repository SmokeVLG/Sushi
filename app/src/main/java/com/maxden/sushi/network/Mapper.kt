package com.maxden.sushi.network

import com.maxden.sushi.database.*
import com.maxden.sushi.model.UserModel


fun List<User>.asDomainUserModel(): List<UserModel> {
    return map {
        UserModel(
            id = it.id,
            name = it.name,
            imgSrcUrl = it.imgSrcUrl
        )
    }
}

