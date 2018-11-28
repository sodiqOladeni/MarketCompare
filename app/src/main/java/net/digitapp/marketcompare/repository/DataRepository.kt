package net.digitapp.marketcompare.repository

import android.arch.lifecycle.MutableLiveData
import net.digitapp.marketcompare.client.ClientEndPoint
import net.digitapp.marketcompare.model.DataModel
import net.digitapp.marketcompare.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepository{
    fun getAllProductList(searchQuery:String):MutableLiveData<DataModel>{
        val data = MutableLiveData<DataModel>()
        val service = RetrofitService().getNetworkInstance()!!.create(ClientEndPoint::class.java)
        val call = service.getProducts(searchQuery)

        call.enqueue(object : Callback<DataModel>{
            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                data.value = response.body()
            }
        })

        return data
    }
}