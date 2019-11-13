package ssun.pe.kr.androiddemo.data

object Naver {

    val apiService: NaverService = RetrofitCreator.create().create(NaverService::class.java)
}