package org.secuso.privacyfriendlyexample

import androidx.lifecycle.LiveData
import org.secuso.pfacore.application.PFApplication
import org.secuso.pfacore.application.PFDatabase
import org.secuso.pfacore.model.about.About
import org.secuso.pfacore.model.settings.ISettings
import org.secuso.pfacore.ui.compose.help.Help
import org.secuso.privacyfriendlyexample.database.AppDatabase

class PFExample: PFApplication() {
    override val applicationName: String
        get() = getString(R.string.app_name)
    override val lightMode: LiveData<Boolean>
        get() = PFSettings(applicationContext).lightMode
    override val database: PFDatabase
        get() = AppDatabase.getInstance(applicationContext)

    override val about: About
        get() = About(
            name = getString(R.string.app_name),
            version = BuildConfig.VERSION_NAME,
            authors = getString(R.string.about_author_names),
            repo = getString(org.secuso.pfacore.R.string.about_github)
        )

    override val help
        get() = Help.build(applicationContext) {
            item {
                title { resource(R.string.help_whatis) }
                description { resource(R.string.help_whatis_answer) }
            }
            item {
                title { resource(R.string.help_feature_one) }
                description { resource(R.string.help_feature_one_answer) }
            }
            item {
                title { resource(R.string.help_privacy) }
                description { resource(R.string.help_privacy_answer) }
            }
            item {
                title { resource(R.string.help_permission) }
                description { resource(R.string.help_permission_answer) }
            }
        }
    override val settings: ISettings<*>
        get() = PFSettings(applicationContext).settings
}