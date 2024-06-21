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

//import org.secuso.pfacore.ui.compose.activities.HelpActivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import androidx.core.app.TaskStackBuilder
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.secuso.pfacore.model.DrawerMenu
import org.secuso.pfacore.ui.compose.activities.DrawerActivity
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
    private var mDrawerLayout: DrawerLayout? = null
    private var mNavigationView: NavigationView? = null

    // Helper
    private val mHandler: Handler = Handler()
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

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Enables back navigation for activities that are launched from the NavBar. See
     * `AndroidManifest.xml` to find out the parent activity names for each activity.
     * @param intent
     */
    private fun createBackStack(intent: Intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val builder = TaskStackBuilder.create(this)
            builder.addNextIntentWithParentStack(intent)
            builder.startActivities()
        } else {
            startActivity(intent)
            finish()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val mainContent = findViewById<View>(R.id.main_content)
        if (mainContent != null) {
            mainContent.alpha = 0f
            mainContent.animate().alpha(1f).duration = MAIN_CONTENT_FADEIN_DURATION.toLong()
        }
    }
}
