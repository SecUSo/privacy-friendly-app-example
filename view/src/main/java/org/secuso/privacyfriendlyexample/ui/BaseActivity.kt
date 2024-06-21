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

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.google.android.material.navigation.NavigationView
import org.secuso.pfacore.model.DrawerMenu
import org.secuso.pfacore.ui.view.activities.DrawerActivity
import org.secuso.privacyfriendlyexample.R

/**
 * This class is a parent class of all activities that can be accessed from the
 * Navigation Drawer (example see MainActivity.java)
 *
 * The default NavigationDrawer functionality is implemented in this class. If you wish to inherit
 * the default behaviour, make sure the content view has a NavigationDrawer with the id 'nav_view',
 * the header should point to 'nav_header_main' and the menu should be loaded from 'main_drawer'.
 *
 * Also the main layout that holds the content of the activity should have the id 'main_content'.
 * This way it will automatically fade in and out every time a transition is happening.
 *
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 * @version 20161225
 */
abstract class BaseActivity : DrawerActivity() {
    companion object {
        // delay to launch nav drawer item, to allow close animation to play
        internal const val NAVDRAWER_LAUNCH_DELAY = 250

        // fade in and fade out durations for the main content when switching between
        // different Activities of the app through the Nav Drawer
        internal const val MAIN_CONTENT_FADEOUT_DURATION = 150
        internal const val MAIN_CONTENT_FADEIN_DURATION = 250
    }

    // Navigation drawer:
    private var mNavigationView: NavigationView? = null

    // Helper
    protected val mSharedPreferences: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }

    protected abstract val navigationDrawerID: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(0, 0)
    }

    override fun drawer(): DrawerMenu = DrawerMenu.build {
        name = getString(R.string.app_name)
        icon = R.mipmap.ic_launcher
        section {
            activity {
                name = getString(R.string.action_main)
                icon = R.drawable.ic_menu_home
                clazz = MainActivity::class.java
                extras = { it.apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP } }
            }
            activity {
                name = getString(R.string.action_game)
                icon = R.drawable.ic_menu_game
                clazz = GameActivity::class.java
            }
        }
        defaultDrawerSection(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContent(layoutResID)
    }

    override fun setContentView(view: View) {

        super.setContent(view)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

//
//        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
//        val toggle = ActionBarDrawerToggle(
//                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
//        mDrawerLayout!!.addDrawerListener(toggle)
//        toggle.syncState()
//
//        selectNavigationItem(navigationDrawerID)

        val mainContent = findViewById<View>(R.id.main_content)
        if (mainContent != null) {
            mainContent.alpha = 0f
            mainContent.animate().alpha(1f).duration = MAIN_CONTENT_FADEIN_DURATION.toLong()
        }
    }
}
