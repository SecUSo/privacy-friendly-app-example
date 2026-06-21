package org.secuso.privacyfriendlyexample

import androidx.work.Configuration
import org.secuso.pfacore.application.PFData
import org.secuso.pfacore.application.RoomDatabaseConfig
import org.secuso.pfacore.ui.PFApplication
import org.secuso.privacyfriendlyexample.database.AppDatabase
import org.secuso.privacyfriendlyexample.ui.MainActivity

class PFExample : PFApplication() {
    override val name: String
        get() = getString(R.string.app_name)

    override val database
        get() = RoomDatabaseConfig(baseContext, AppDatabase.DB_NAME, AppDatabase::class.java)
    override val data
        get() = PFApplicationData.instance(baseContext).data
    override val mainActivity = MainActivity::class.java
    override fun getWorkManagerConfiguration(): Configuration {
        TODO("Not yet implemented")
    }
}