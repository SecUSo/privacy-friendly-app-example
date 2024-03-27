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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.method.LinkMovementMethod

import org.secuso.privacyfriendlyexample.BuildConfig
import org.secuso.privacyfriendlyexample.R
import org.secuso.privacyfriendlyexample.databinding.ActivityAboutBinding

/**
 * This activity manages the AboutPage.
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 * Created on 15.06.16.
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.mainContent.alpha = 0f
        binding.mainContent.animate().alpha(1f).duration = BaseActivity.MAIN_CONTENT_FADEIN_DURATION.toLong()

        overridePendingTransition(0, 0)

        binding.secusoWebsite.movementMethod = LinkMovementMethod.getInstance()
        binding.githubURL.movementMethod = LinkMovementMethod.getInstance()
        binding.textFieldVersion.text = getString(R.string.version_number, BuildConfig.VERSION_NAME)
    }
}

