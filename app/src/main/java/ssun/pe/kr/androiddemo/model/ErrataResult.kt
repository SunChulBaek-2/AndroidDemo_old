package ssun.pe.kr.androiddemo.model

import com.google.gson.annotations.SerializedName

data class ErrataResult(
    @SerializedName("errata") val errata: String
)