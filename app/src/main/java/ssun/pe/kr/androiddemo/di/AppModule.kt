package ssun.pe.kr.androiddemo.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ssun.pe.kr.androiddemo.data.NaverDataSource
import ssun.pe.kr.androiddemo.data.NaverRepository
import ssun.pe.kr.androiddemo.data.LocalNaverDataSource
import ssun.pe.kr.androiddemo.data.RemoteNaverDataSource
import ssun.pe.kr.androiddemo.view.detail.DetailViewModel
import ssun.pe.kr.androiddemo.view.main.MainViewModel

val appModule = module {
    single { NaverRepository(get("local"), get("remote")) }

    single<NaverDataSource>("local") { LocalNaverDataSource() }

    single<NaverDataSource>("remote") { RemoteNaverDataSource() }

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel() }
}