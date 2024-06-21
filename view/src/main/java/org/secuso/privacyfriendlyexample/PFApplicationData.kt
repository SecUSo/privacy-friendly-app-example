package org.secuso.privacyfriendlyexample

import android.content.Context
import androidx.lifecycle.map
import org.secuso.pfacore.application.PFData
import org.secuso.pfacore.model.Theme
import org.secuso.pfacore.model.about.About
import org.secuso.pfacore.model.preferences.Preferable
import org.secuso.pfacore.model.preferences.settings.ISettingData
import org.secuso.pfacore.ui.view.help.Help
import org.secuso.pfacore.ui.view.preferences.appPreferences
import org.secuso.pfacore.ui.view.preferences.settings.PreferenceFirstTimeLaunch
import org.secuso.pfacore.ui.view.preferences.settings.SettingThemeSelector
import org.secuso.pfacore.ui.view.tutorial.buildTutorial

class PFApplicationData private constructor(context: Context) {

    lateinit var theme: ISettingData<String>
        private set
    lateinit var exampleSwitch: Preferable<Boolean>
        private set
    lateinit var firstTimeLaunch: Preferable<Boolean>
        private set

    private val preferences = appPreferences(context) {
        preferences {
            firstTimeLaunch = PreferenceFirstTimeLaunch().build().invoke(this)
        }
        settings {
            category("Example Category") {
                exampleSwitch = switch {
                    key = "pref_example_switch"
                    title { resource(R.string.pref_example_switch) }
                    summary { resource(R.string.pref_example_summary) }
                    default = false
                    backup = true
                }
            }
            category("Design") {
                theme = SettingThemeSelector().build().invoke(this)
            }
            category("Legal") {
                menu("Legal") {
                    setting {
                        menu {
                            title { literal("Legal") }
                        }
                    }
                    content {

                    }
                }
            }
        }
    }

    private val help = Help.build(context) {
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

    private val about = About(
        name = context.resources.getString(R.string.app_name),
        version = BuildConfig.VERSION_NAME,
        authors = context.resources.getString(R.string.about_author_names),
        repo = context.resources.getString(org.secuso.pfacore.R.string.about_github)
    )

    private val tutorial = buildTutorial {
        stage {
            title = context.getString(R.string.slide1_heading)
            images = listOf(R.mipmap.ic_splash)
            description = context.getString(R.string.slide1_text)
        }
        stage {
            title = context.getString(R.string.slide2_heading)
            images = listOf(R.mipmap.ic_splash)
            description = context.getString(R.string.slide2_text)
        }
        stage {
            title = context.getString(R.string.slide3_heading)
            images = listOf(R.mipmap.ic_splash)
            description = context.getString(R.string.slide3_text)
        }
    }

    val data = PFData(
        about = about,
        help = help,
        settings = preferences.settings,
        tutorial = tutorial,
        theme = theme.state.map { Theme.valueOf(it) },
        firstLaunch = firstTimeLaunch
    )

    companion object {
        private var _instance: PFApplicationData? = null
        fun instance(context: Context): PFApplicationData {
            if (_instance == null) {
                _instance = PFApplicationData(context)
            }
            return _instance!!
        }

    }
}


