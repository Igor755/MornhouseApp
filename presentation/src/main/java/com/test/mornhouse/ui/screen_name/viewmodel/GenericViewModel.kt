package com.test.mornhouse.ui.screen_name.viewmodel

import androidx.lifecycle.ViewModel
import com.test.domain.interactor.GenericInteractor

class GenericViewModel(
    private val interactor: GenericInteractor
) : ViewModel()