package net.digitapp.marketcompare.model

import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("image")
    var itemImage: String = "",
    @field:SerializedName("price")
    var itemPrice: String = "",
    @field:SerializedName("source")
    var marketSource: String = "",
    @field:SerializedName("title")
    var itemTitle: String = "",
    @field:SerializedName("url")
    var itemUrl: String = "",
    var productList:List<DataModel>? = null
)