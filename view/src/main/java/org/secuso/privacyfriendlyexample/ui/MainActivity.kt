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
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.secuso.pfacore.application.PFApplication
import org.secuso.pfacore.model.DrawerElement

import org.secuso.privacyfriendlyexample.R
import org.secuso.privacyfriendlyexample.ui.viewmodel.MainExampleViewModel

/**
 * This class displays some example Buttons and shows the usage of the database.
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 */
class MainActivity : BaseActivity() {
    /**
     * ID of the menu item it belongs to
     */
    override val navigationDrawerID: Int = R.id.nav_example

    private lateinit var exampleViewModel: MainExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // This will set the theme to the selected theme in the preferences.
        PFApplication.instance.data.theme.observe(this) { it.apply() }

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
