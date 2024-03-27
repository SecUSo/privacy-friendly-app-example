package org.secuso.privacyfriendlyexample

import org.secuso.pfacore.model.PFApplication
import org.secuso.pfacore.model.PFDatabase
import org.secuso.pfacore.ui.AboutData
import org.secuso.pfacore.ui.help.HelpData
import org.secuso.pfacore.ui.settings.ISettings
import org.secuso.privacyfriendlyexample.database.AppDatabase

class PFExample: PFApplication() {
    override val ApplicationName: String
        get() = getString(R.string.app_name)
    override val LightMode: Boolean
        get() = PFSettings(applicationContext).lightMode
    override val Database: PFDatabase
        get() = AppDatabase.getInstance(applicationContext)

    override val About: AboutData
        get() = AboutData(
            name = getString(R.string.app_name),
            version = BuildConfig.VERSION_NAME,
            authors = getString(R.string.about_author_names),
            repo = getString(org.secuso.pfacore.R.string.about_github)
        )

    override val Help
        get() = HelpData.build(applicationContext) {
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
    override val Settings: ISettings
        get() = PFSettings(applicationContext).settings
}