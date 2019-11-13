package ssun.pe.kr.androiddemo.model

import com.google.gson.annotations.SerializedName

data class ImageResult(
    @SerializedName("items") val items: List<ImageItem>
)

data class ImageItem(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("sizeheight") val height: String,
    @SerializedName("sizewidth") val width: String
)