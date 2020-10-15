package com.nik.mycar.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nik.mycar.data.CarRepo

class CarDetailsVM @ViewModelInject constructor(
    private val carRepo: CarRepo,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

}
