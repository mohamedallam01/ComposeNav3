package com.sample.composenav3.di

import com.sample.composenav3.ui.auth.login.LoginViewModel
import com.sample.composenav3.ui.auth.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegisterViewModel() }
}
