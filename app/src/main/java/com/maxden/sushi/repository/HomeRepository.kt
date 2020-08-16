package com.maxden.sushi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.maxden.sushi.database.DatabaseCart
import com.maxden.sushi.database.DatabaseItemType3
import com.maxden.sushi.database.Databases
import com.maxden.sushi.model.AccountModel
import com.maxden.sushi.model.CartModel
import com.maxden.sushi.model.ItemTypeModel
import com.maxden.sushi.model.KitTypeModel
import com.maxden.sushi.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {

    val cartItemsForRecView: LiveData<List<CartModel>> =
        Transformations.map(database.commonDao.getCartForRecView()) { it.asDomainCartModel() }
    val orders: LiveData<List<AccountModel>> =
        Transformations.map(database.commonDao.getAccount()) { it.asDomainAccountModel() }
    val kits: LiveData<List<KitTypeModel>> =
        Transformations.map(database.commonDao.getKitType()) { it.asDomainKitTypeModel() }
    val items1: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType1()) { it.asDomainItemType1Model() }
    val items2: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType2()) { it.asDomainItemType2Model() }
    val items3: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType3()) { it.asDomainItemType3Model() }

    suspend fun refreshKitsAndItems() {
        withContext(Dispatchers.IO) {

            val temp: MutableList<DatabaseItemType3> = mutableListOf()

            val itemType1 = DatabaseItemType3(
                "1",
                "Drafter1",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSg3oxXxnbGRC3p7IjejzvQQI8RRk7UkVSMJkOuhR922AmVJAbN&usqp=CAU",
                "1 рубль"
            )
            val itemType2 = DatabaseItemType3(
                "2",
                "Drafter2",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQIZWBsk38xkCleyWVIH0swFMqCMa8GOAbECSl2zMbTVNScagQObmRQOI-gSw&usqp=CAc",
                "2 рубля"
            )
            val itemType3 = DatabaseItemType3(
                "3",
                "Drafter3",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRBdGFqF9YJ2XmFHsxnKSllKenFx_Sx19YE95pJL0fcMHqrQpjZ&usqp=CAU",
                "3 рубля"
            )
            val itemType4 = DatabaseItemType3(
                "4",
                "Drafter4",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU",
                "4 рубля"
            )
            val itemType5 = DatabaseItemType3(
                "5",
                "Drafter5",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU",
                "5 рублей"
            )
            val itemType6 = DatabaseItemType3(
                "6",
                "Drafter6",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRh4gY8XVZ3DP-VVm-MIcnq5Bbct7nMmX_8EerpdlXXI2asj0-I&usqp=CAU",
                "5 рублей"
            )
            temp.add(itemType1)
            temp.add(itemType2)
            temp.add(itemType3)
            temp.add(itemType4)
            temp.add(itemType4)
            temp.add(itemType5)
            temp.add(itemType6)

            database.commonDao.insertAllItems3(temp.toList())
        }
    }

    suspend fun add(id: Int){
        withContext(Dispatchers.IO) {
            val kitsForCart = database.commonDao.getKitTypeForCart()
            database.commonDao.insertInCart(kitsForCart[id-1].asDatabaseCartType())
        }
    }

    suspend fun remove(id: Int) {
        withContext(Dispatchers.IO) {
            database.commonDao.remove(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            val listCart = database.commonDao.getCartForAccount()
            val price = calculateTotalBill(listCart)
            database.commonDao.insertAccount(price)
            delay(2200)
            database.commonDao.deleteAll()
        }
    }

    private fun calculateTotalBill(listCart : List<DatabaseCart>): Int{
        var sum = 0
        for (items in listCart) {
            sum += items.price.substring(1).toInt()
        }
        return sum
    }

}