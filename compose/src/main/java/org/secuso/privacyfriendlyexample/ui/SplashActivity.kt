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
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import org.secuso.privacyfriendlyexample.ui.helper.FirstLaunchManager

/**
 * SplashScreen when the App is started.
 * It will check if the app was started before and start the Tutorial or the MainActivity.
 * The logo for the Splashscreen is set in the style for it.
 *
 * @author Karola Marky (yonjuni), Christopher Beckmann (Kamuno)
 * Created on 22.10.16
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainIntent = if (FirstLaunchManager(this).isFirstTimeLaunch) {
            Intent(this, TutorialActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(mainIntent)
        finish()
    }
}
