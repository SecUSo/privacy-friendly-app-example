package org.secuso.privacyfriendlyexample

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import org.secuso.pfacore.ui.settings.CommonSettings
import org.secuso.pfacore.ui.settings.ISettings
import org.secuso.pfacore.ui.settings.Settings
class PFSettings(context: Context) {

    companion object {
        private var _settings: ISettings? = null
    }

    init {
        if (_settings == null) {
            _settings = Settings.build(context) {
                category("Example Category") {
                    switch {
                        key = "pref_example_switch"
                        title { resource(R.string.pref_example_switch) }
                        summary { resource(R.string.pref_example_summary) }
                        default = false
                        backup = true
                    }
                }
                category("Design") {
                    CommonSettings.themeSelector(context).invoke(this)
                }
            }
        }
    }

    val settings
        get() = _settings!!

    val lightMode
        get() = settings.all.map { it.data }.find { it.key === CommonSettings.themeSelectorKey }!!.state.value.toString().toInt() == AppCompatDelegate.MODE_NIGHT_NO
}