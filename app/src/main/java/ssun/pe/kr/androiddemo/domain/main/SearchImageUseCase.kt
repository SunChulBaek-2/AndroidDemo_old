package ssun.pe.kr.androiddemo.domain.main

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import ssun.pe.kr.androiddemo.data.main.SearchImageFactory
import ssun.pe.kr.androiddemo.domain.FlowableUseCase
import ssun.pe.kr.androiddemo.model.ImageItem

class SearchImageUseCase(
    private val disposables: CompositeDisposable
) : FlowableUseCase<String, PagedList<ImageItem>>() {

    private lateinit var factory: SearchImageFactory

    override fun execute(p: String): Flowable<PagedList<ImageItem>> =
        RxPagedListBuilder<Long, ImageItem>(
            SearchImageFactory(disposables, p).apply {
                this@SearchImageUseCase.factory = this
            }, PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(20) // 설정하지 않을 경우 page size * 3
                .setPageSize(20).build()
        ).buildFlowable(BackpressureStrategy.BUFFER)

    fun refresh() = factory.refresh()
}