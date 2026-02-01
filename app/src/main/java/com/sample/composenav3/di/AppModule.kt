package com.sample.composenav3.di

import com.sample.composenav3.data.FakeRepo
import com.sample.composenav3.data.FakeRepoImpl
import com.sample.composenav3.ui.auth.login.LoginViewModel
import com.sample.composenav3.ui.auth.register.RegisterViewModel
import com.sample.composenav3.ui.main.details.DetailsViewModel
import com.sample.composenav3.ui.main.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FakeRepo> { FakeRepoImpl() }

    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
