package com.amosh.gctestase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amosh.gctestase.bases.BaseViewModel
import com.amosh.gctestase.data.AppResult
import com.amosh.gctestase.data.FilterBody
import com.amosh.gctestase.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    fun setEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetListOfCare -> handleGetListOfCare(event.filterBody)
        }
    }

    private val _carsList: MutableLiveData<AppResult> =
        MutableLiveData()
    val carsList: LiveData<AppResult>
        get() = _carsList

    private fun handleGetListOfCare(filterBody: FilterBody?) = viewModelScope.launch {
        val result = repository.getCarsList()
        when (filterBody?.hasParams) {
            true -> {
                val carsList = result.cars?.filter {
                    (it.color == filterBody.color)
                        || it.unit_price == filterBody.price
                }
                _carsList.value = when {
                    carsList.isNullOrEmpty() -> AppResult.SuccessWithEmptyList()
                    else -> AppResult.SuccessWithList(cars = carsList)
                }
            }
            else -> _carsList.value = result
        }
    }

    sealed class MainEvent {
        data class GetListOfCare(val filterBody: FilterBody?) : MainEvent()
    }
}
