package org.secuso.privacyfriendlyexample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.method.LinkMovementMethod

import kotlinx.android.synthetic.main.activity_about.*

import org.secuso.privacyfriendlyexample.BuildConfig
import org.secuso.privacyfriendlyexample.R

/**
 * This activity manages the AboutPage.
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 * Created on 15.06.16.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        main_content.alpha = 0f
        main_content.animate().alpha(1f).duration = BaseActivity.MAIN_CONTENT_FADEIN_DURATION.toLong()

        overridePendingTransition(0, 0)

        secusoWebsite.movementMethod = LinkMovementMethod.getInstance()
        githubURL.movementMethod = LinkMovementMethod.getInstance()
        textFieldVersionName.text = BuildConfig.VERSION_NAME
    }
}

