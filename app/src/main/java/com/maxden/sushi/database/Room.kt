package com.maxden.sushi.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CommonDao{
    //For user
    @Query("select * from user")
    fun getAllUser(): LiveData<List<User>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(items: List<User>)

}

@Database(entities = [User::class], version = 1)
abstract class Databases:RoomDatabase(){
    abstract val commonDao: CommonDao
}

private lateinit var INSTANCE: Databases

//creates database when not initialized
fun getDatabase(context: Context): Databases {
    synchronized(Databases::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                Databases::class.java,
                "databases").build()
        }
    }
    return INSTANCE
}