package ssun.pe.kr.androiddemo.data.model

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("productId") val productId: Long,
        @SerializedName("title") val title: String,
        @SerializedName("link") val link: String,
        @SerializedName("image") val image: String,
        @SerializedName("lprice") val lprice: String,
        @SerializedName("hprice") val hprice: String)