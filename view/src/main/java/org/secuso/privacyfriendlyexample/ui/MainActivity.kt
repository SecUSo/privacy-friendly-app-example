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
package org.secuso.privacyfriendlyexample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.secuso.pfacore.application.PFApplication
import org.secuso.pfacore.model.DrawerElement
import org.secuso.pfacore.model.permission.PFAPermission
import org.secuso.pfacore.ui.declareUsage

import org.secuso.privacyfriendlyexample.R
import org.secuso.privacyfriendlyexample.ui.viewmodel.MainExampleViewModel

/**
 * This class displays some example Buttons and shows the usage of the database.
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 */
class MainActivity : BaseActivity() {

    private lateinit var exampleViewModel: MainExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // This will set the theme to the selected theme in the preferences.
        PFApplication.instance.data.theme.observe(this) { it.apply() }

        val requestPermission = PFAPermission.AccessCoarseLocation.declareUsage(this) {
            onGranted = {
                Log.d("TestPermission", "permission should be granted: ${ContextCompat.checkSelfPermission(activity, PFAPermission.ScheduleExactAlarm.permission)}")
            }
            onDenied = {
                Log.d("TestPermission", "permission should be denied: ${ContextCompat.checkSelfPermission(activity, PFAPermission.ScheduleExactAlarm.permission)}")
            }
            showRationale = {
                rationaleTitle = "This requires the schedule exact alarm permission"
                rationaleText = "Definitely needed."
            }
        }
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { requestPermission() }

        findViewById<Button>(R.id.crash_button).setOnClickListener { throw IllegalStateException("This application was crashed on purpose!") }

        exampleViewModel = ViewModelProviders.of(this).get(MainExampleViewModel::class.java)
        exampleViewModel.sampleData.observe(this, Observer { data ->
            // TODO do something with the data here - e.g. update an adapter, trigger some event, etc.
        })

        overridePendingTransition(0, 0)
    }

    override fun isActiveDrawerElement(element: DrawerElement): Boolean {
        return element.name == getString(R.string.action_main)
    }

    fun onClick(view: View) {
        // do something with all these buttons?
        when (view.id) {
            else -> {}
        }
    }
}
