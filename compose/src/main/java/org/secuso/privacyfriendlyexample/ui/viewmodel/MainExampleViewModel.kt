/*
 This file is part of Privacy Friendly App Example.

 Privacy Friendly App Example is free software:
 you can redistribute it and/or modify it under the terms of the
 GNU General Public License as published by the Free Software Foundation,
 either version 3 of the License, or any later version.

 Privacy Friendly App Example is distributed in the hope
 that it will be useful, but WITHOUT ANY WARRANTY; without even
 the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Privacy Friendly App Example. If not, see <http://www.gnu.org/licenses/>.
 */
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
import org.secuso.privacyfriendlyexample.database.AppDatabase
import org.secuso.privacyfriendlyexample.database.model.SampleData

/**
 * This is a very basic example ViewModel, that provides the SampleData for the View.
 * @see MainActivity
 * @author Christopher Beckmann (Kamuno)
 */
class MainExampleViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = MainExampleViewModel::class.java.simpleName

    // Coroutine Helpers
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Data
    private val preferences = PreferenceManager.getDefaultSharedPreferences(app)
    private val database = AppDatabase.getInstance(app)

    // LiveData for View
    private val _sampleData = MediatorLiveData<List<SampleData>>()
    val sampleData: LiveData<List<SampleData>> get() = _sampleData

    // Load initial required data. This does not have to be done here.
    init {
        loadSampleData()
    }

    private fun loadSampleData() {
        _sampleData.addSource(database.sampleDataDao().allLive) {
            _sampleData.value = it
        }
    }

    // Cleanup
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        Log.d(TAG, "destroyed!")
    }

}