package org.secuso.privacyfriendlyexample.ui.viewmodel

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.secuso.privacyfriendlyexample.database.AppDatabase
import org.secuso.privacyfriendlyexample.database.model.SampleData

/**
 * This is a very basic example ViewModel, that provides the SampleData for the View.
 * @see MainActivity
 * @author Christopher Beckmann (Kamuno)
 */
class MainExampleViewModel(app : Application) : AndroidViewModel(app) {
    private val TAG = MainExampleViewModel::class.java.simpleName

    // Coroutine Helpers
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Data
    private val preferences = PreferenceManager.getDefaultSharedPreferences(app)
    private val database = AppDatabase.getInstance(app)

    // LiveData for View
    private val _sampleData = MediatorLiveData<List<SampleData>>()
    val sampleData : LiveData<List<SampleData>> get() = _sampleData

    // Load initial required data. This does not have to be done here.
    init {
        loadSampleData()
    }

    private fun loadSampleData() {
        uiScope.launch(Dispatchers.IO) {
            _sampleData.addSource(database.sampleDataDao().allLive) {
                _sampleData.value = it
            }
        }
    }

    // Cleanup
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.d(TAG, "destroyed!")
    }

}