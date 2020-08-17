package com.maxden.sushi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.maxden.sushi.database.Databases
import com.maxden.sushi.database.User
import com.maxden.sushi.model.UserModel
import com.maxden.sushi.network.asDomainUserModel
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
                "Mike",
                "https://s1.iconbird.com/ico/0612/vistabasesoftwareicons/w256h2561339252913User1.png"
            )
            val user2 = User(
                "2",
                "Jane",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGanxUkDEQCdgSatiOCehm8zfbt4SmcUjiKx8L45vltShvwd4u&s"
            )
            val user3 = User(
                "3",
                "Max",
                "https://s1.iconbird.com/ico/0612/vistabasesoftwareicons/w256h2561339252913User1.png"
            )
            val user4 = User(
                "4",
                "John",
                "https://s1.iconbird.com/ico/0612/vistabasesoftwareicons/w256h2561339252913User1.png"
            )
            val user5 = User(
                "5",
                "Mike",
                "https://s1.iconbird.com/ico/0612/vistabasesoftwareicons/w256h2561339252913User1.png"
            )
            val user6 = User(
                "6",
                "Vladimir",
                "https://s1.iconbird.com/ico/0612/vistabasesoftwareicons/w256h2561339252913User1.png"
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