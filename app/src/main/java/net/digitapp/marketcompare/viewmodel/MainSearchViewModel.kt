package net.digitapp.marketcompare.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import net.digitapp.marketcompare.model.DataModel
import net.digitapp.marketcompare.repository.DataRepository

class MainSearchViewModel : ViewModel() {
    val dataRepo = DataRepository()

    fun getProductLists(userQuery:String):MutableLiveData<DataModel>{
        return dataRepo.getAllProductList(userQuery)
    }
}
