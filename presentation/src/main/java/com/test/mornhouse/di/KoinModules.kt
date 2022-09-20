package com.test.mornhouse.di

import com.test.domain.interactor.FactNumberInteractor
import com.test.mornhouse.ui.screen_name.viewmodel.GenericViewModel
import com.test.domain.interactor.GenericInteractor
import com.test.mornhouse.ui.main.adapter.FactAdapter
import com.test.mornhouse.viemodel.FactNumberViewModel
import org.koin.dsl.module

val adaptersModule = module {
    factory { FactAdapter() }
}

val viewModelsModule = module {
    factory { GenericViewModel(get()) }
    factory { FactNumberViewModel(get()) }

}

val interactorsModule = module {
    factory { GenericInteractor() }
    factory { FactNumberInteractor() }

}

