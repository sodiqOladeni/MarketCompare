package net.digitapp.marketcompare.client

import net.digitapp.marketcompare.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientEndPoint {
    @GET("search/{user_search}")
    fun getProducts(
        @Path("user_search") userSearch:String
    ): Call<DataModel>
}