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
package org.secuso.privacyfriendlyexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import org.secuso.pfacore.application.PFDatabase
import org.secuso.privacyfriendlyexample.database.dao.SampleDataDao
import org.secuso.privacyfriendlyexample.database.model.SampleData

/**
 * This is the main Database Class. To access the database you have to get the instance of the class and access the data via the dao classes.
 * @author Christopher Beckmann (Kamuno)
 */
@Database(entities = arrayOf(SampleData::class), version = 1, exportSchema = false)
abstract class AppDatabase : PFDatabase() {
    override val name: String = DB_NAME

    abstract fun sampleDataDao(): SampleDataDao

    companion object {
        val DB_NAME = "pfa_example"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
