package com.maxden.sushi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.maxden.sushi.database.User
import com.maxden.sushi.database.Databases
import com.maxden.sushi.model.UserModel
import com.maxden.sushi.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {


    val users: LiveData<List<UserModel>> =
        Transformations.map(database.commonDao.getAllUser()) { it.asDomainUserModel() }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {

            val users: MutableList<User> = mutableListOf()

            val user1 = User(
                "1",
                "Drafter1",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSg3oxXxnbGRC3p7IjejzvQQI8RRk7UkVSMJkOuhR922AmVJAbN&usqp=CAU"
            )
            val user2 = User(
                "2",
                "Drafter2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQIZWBsk38xkCleyWVIH0swFMqCMa8GOAbECSl2zMbTVNScagQObmRQOI-gSw&usqp=CAc"
            )
            val user3 = User(
                "3",
                "Drafter3",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBdGFqF9YJ2XmFHsxnKSllKenFx_Sx19YE95pJL0fcMHqrQpjZ&usqp=CAU"
            )
            val user4 = User(
                "4",
                "Drafter4",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU"
            )
            val user5 = User(
                "5",
                "Drafter5",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU"
            )
            val user6 = User(
                "6",
                "Drafter6",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU"
            )
            users.add(user1)
            users.add(user2)
            users.add(user3)
            users.add(user4)
            users.add(user4)
            users.add(user5)
            users.add(user6)

            database.commonDao.insertAllUser(users.toList())
        }
    }



}