package org.secuso.privacyfriendlyexample.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import androidx.core.app.TaskStackBuilder
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import android.view.View

import org.secuso.privacyfriendlyexample.R

/**
 * This class is a parent class of all activities that can be accessed from the
 * Navigation Drawer (example see MainActivity.java)
 *
 * It handles the NavigationDrawer logic and some animation for fade-in and fade-out.
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 * @version 20161225
 */
abstract class BaseActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
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

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean = goToNavigationItem(item.itemId)

    protected fun goToNavigationItem(itemId: Int): Boolean {
        if (itemId == navigationDrawerID) {
            // just close drawer because we are already in this activity
            mDrawerLayout?.closeDrawer(GravityCompat.START)
            return true
        }

        // delay transition so the drawer can close
        mHandler.postDelayed({ callDrawerItem(itemId) }, NAVDRAWER_LAUNCH_DELAY.toLong())

        mDrawerLayout?.closeDrawer(GravityCompat.START)

        selectNavigationItem(itemId)

        // fade out the active activity
        val mainContent = findViewById<View>(R.id.main_content)
        mainContent?.animate()!!.alpha(0f).duration = MAIN_CONTENT_FADEOUT_DURATION.toLong()
        return true
    }

    // set active navigation item
    private fun selectNavigationItem(itemId: Int) {
        mNavigationView ?: return

        for (i in 0 until mNavigationView!!.menu.size()) {
            val b = itemId == mNavigationView!!.menu.getItem(i).itemId
            mNavigationView!!.menu.getItem(i).isChecked = b
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

    /**
     * This method manages the behaviour of the navigation drawer
     * Add your menu items (ids) to res/menu/activity_main_drawer.xml
     * @param itemId Item that has been clicked by the user
     */
    private fun callDrawerItem(itemId: Int) {

        val intent: Intent

        when (itemId) {
            R.id.nav_example -> {
                intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
            R.id.nav_game -> {
                intent = Intent(this, GameActivity::class.java)
                createBackStack(intent)
            }
            R.id.nav_about -> {
                intent = Intent(this, AboutActivity::class.java)
                createBackStack(intent)
            }
            R.id.nav_help -> {
                intent = Intent(this, HelpActivity::class.java)
                createBackStack(intent)
            }
            R.id.nav_settings -> {
                intent = Intent(this, SettingsActivity::class.java)
                intent.putExtra(PreferenceActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.GeneralPreferenceFragment::class.java.name)
                intent.putExtra(PreferenceActivity.EXTRA_NO_HEADERS, true)
                createBackStack(intent)
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        if (supportActionBar == null) {
            setSupportActionBar(toolbar)
        }

        mDrawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById<View>(R.id.nav_view) as NavigationView
        mNavigationView!!.setNavigationItemSelectedListener(this)

        selectNavigationItem(navigationDrawerID)

        val mainContent = findViewById<View>(R.id.main_content)
        if (mainContent != null) {
            mainContent.alpha = 0f
            mainContent.animate().alpha(1f).duration = MAIN_CONTENT_FADEIN_DURATION.toLong()
        }
    }
}
