package ssun.pe.kr.androiddemo.data.model

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("title") val title: String,
        @SerializedName("link") val link: String,
        @SerializedName("image") val image: String,
        @SerializedName("lprice") val lprice: String,
        @SerializedName("hprice") val hprice: String
)