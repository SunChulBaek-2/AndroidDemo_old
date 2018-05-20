package ssun.pe.kr.androiddemo.data.model

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("total") val total: Int,
        @SerializedName("items") val items: List<Item>
)