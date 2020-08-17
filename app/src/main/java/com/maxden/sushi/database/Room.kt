package com.maxden.sushi.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface CommonDao{




    //For item type 3
    @Query("select * from databaseitemtype3")
    fun getItemType3(): LiveData<List<DatabaseItemType3>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems3( items: List<DatabaseItemType3>)



}

@Database(entities = [DatabaseItemType3::class], version = 1)
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