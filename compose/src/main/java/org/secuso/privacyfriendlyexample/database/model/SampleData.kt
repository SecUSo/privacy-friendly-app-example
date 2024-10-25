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
package org.secuso.privacyfriendlyexample.database.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * The Model class for the SampleData
 */
@Entity(tableName = "sample_data")
data class SampleData(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id")
        var identifier: Int,
        @ColumnInfo(name = "content")
        var content: String?) : Parcelable {

    constructor() : this(0,null)
    @Ignore constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(identifier)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SampleData> {
        override fun createFromParcel(parcel: Parcel): SampleData {
            return SampleData(parcel)
        }

        override fun newArray(size: Int): Array<SampleData?> {
            return arrayOfNulls(size)
        }
    }
}