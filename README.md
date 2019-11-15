# AndroidDemo [![CircleCI (all branches)](https://img.shields.io/circleci/project/github/x1210x/AndroidDemo.svg)](https://circleci.com/gh/x1210x/AndroidDemo)
* API 호출과 에러 핸들링
  * Paging Library 연동 : [쇼핑](https://developers.naver.com/docs/search/shopping/), [이미지](https://developers.naver.com/docs/search/image/)
    * [Paging With Network Sample](https://github.com/android/architecture-components-samples/tree/master/PagingWithNetworkSample)의 [Listing](https://github.com/android/architecture-components-samples/blob/master/PagingWithNetworkSample/lib/src/main/java/com/android/example/paging/pagingwithnetwork/reddit/repository/Listing.kt) 클래스 사용
  * 단순 API 호출 : [오타변환](https://developers.naver.com/docs/search/errata/) API
    * [Once](https://github.com/x1210x/AndroidDemo/blob/master/app/src/main/java/ssun/pe/kr/androiddemo/presentation/Once.kt) (아래 Listing 단순화 버전) 클래스 사용
* Architecture
  * [Data Binding](https://developer.android.com/topic/libraries/data-binding/)
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  * [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* 라이브러리
  * [Coroutines](https://kotlinlang.org/docs/reference/coroutines.html)
  * [Glide](https://github.com/bumptech/glide)
  * ~[Kotlin Coroutine Adapter](https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter)~ (by [Retrofit suspend support](https://github.com/square/retrofit/blob/master/CHANGELOG.md#version-260-2019-06-05))
  * [Retrofit](https://github.com/square/retrofit)
  * [Timber](https://github.com/JakeWharton/timber)
* 레퍼런스
  * [SwipeRefreshLayout + ViewPager](https://stackoverflow.com/questions/25978462/swiperefreshlayout-viewpager-limit-horizontal-scroll-only)
  * [Paging Library error handling](https://github.com/android/architecture-components-samples/tree/master/PagingWithNetworkSample)