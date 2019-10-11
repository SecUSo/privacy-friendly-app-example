package org.secuso.privacyfriendlyexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.secuso.privacyfriendlyexample.database.dao.SampleDataDao
import org.secuso.privacyfriendlyexample.database.model.SampleData

/**
 *
 * @author Christopher Beckmann (Kamuno)
 */
@Database(entities = arrayOf(SampleData::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

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
