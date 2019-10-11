package org.secuso.privacyfriendlyexample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import org.secuso.privacyfriendlyexample.tutorial.PrefManager
import org.secuso.privacyfriendlyexample.tutorial.TutorialActivity

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

        val mainIntent = if (PrefManager(this).isFirstTimeLaunch) {
            Intent(this, TutorialActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(mainIntent)
        finish()
    }
}
