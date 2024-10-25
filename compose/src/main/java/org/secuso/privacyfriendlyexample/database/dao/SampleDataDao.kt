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
package org.secuso.privacyfriendlyexample.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.secuso.privacyfriendlyexample.database.model.SampleData

/**
 * SampleDataDao is used to access the database.
 */
@Dao
interface SampleDataDao {
    @get:Query("SELECT * FROM sample_data")
    val all: List<SampleData>

    @get:Query("SELECT * FROM sample_data")
    val allLive: LiveData<List<SampleData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sampleData: SampleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg hosts: SampleData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(hosts: List<SampleData>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(host: SampleData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateAll(vararg host: SampleData)

    @Query("SELECT * FROM sample_data WHERE content = :content")
    operator fun get(content: String): SampleData?

    @Query("SELECT * FROM sample_data WHERE _id = :id")
    operator fun get(id: Int): SampleData?

    @Query("SELECT count(*) FROM sample_data")
    fun count(): LiveData<Int>

    @Delete
    fun delete(domain: SampleData)

    @Delete
    fun deleteAll(deleteList: List<SampleData>)
}