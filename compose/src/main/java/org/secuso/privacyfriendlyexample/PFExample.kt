package org.secuso.privacyfriendlyexample

import android.app.Activity
import org.secuso.pfacore.application.PFApplication
import org.secuso.pfacore.application.PFData
import org.secuso.privacyfriendlyexample.database.AppDatabase
import org.secuso.privacyfriendlyexample.ui.MainActivity

class PFExample: PFApplication() {
    override val name: String
        get() = getString(R.string.app_name)

    override val databaseName = AppDatabase.DB_NAME
    override val database = AppDatabase::class.java
    override val data: PFData
        get() = PFApplicationData.instance(applicationContext).data
    override val mainActivity: Class<out Activity> = MainActivity::class.java
}