package com.tobzzo.mmmpk.ui.dashboardView.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tobzzo.mmmpk.helpers.*
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import com.tobzzo.mmmpk.network.ApiService
import com.tobzzo.mmmpk.network.ApiServiceInterface
import io.reactivex.disposables.Disposable

class DashboardViewModel(
    private val apiService: ApiServiceInterface = ApiService()
) : ViewModel(), DashboardViewModelInterface {

    override val departures: LiveData<List<DepartureModel>>
        get() = departuresData

    override val progress: LiveData<Boolean>
        get() = progressData

    override val errors: LiveData<ErrorMessage>
        get() = errorsData

    override val departuresCount: Int
        get() = departuresData.value?.count() ?: 0

    private val departuresData = MutableLiveData<List<DepartureModel>>()
    private val progressData = MutableLiveData<Boolean>(false)
    private val errorsData = MutableLiveData<ErrorMessage>()

    private var disposable: Disposable? = null
    override fun getDeparturesData() {
        disposable?.dispose()
        disposable = apiService.getAllDepartures()
            .subscribeOnIOThread()
            .observeOnMainThread()
            .withProgress(progressData)
            .showErrorMessages(errorsData)
            .subscribe {
                departuresData.value = it
            }
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}