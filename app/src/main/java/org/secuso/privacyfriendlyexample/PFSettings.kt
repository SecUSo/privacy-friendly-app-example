package org.secuso.privacyfriendlyexample

import android.content.Context
import androidx.lifecycle.map
import org.secuso.pfacore.model.settings.ISettings
import org.secuso.pfacore.ui.compose.settings.Settings
import org.secuso.pfacore.ui.compose.settings.SettingThemeSelector

class PFSettings(context: Context) {

    companion object {
        private var _settings: ISettings<*>? = null
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
                    SettingThemeSelector().build().invoke(this)
                }
                category("Legal") {
                    menu("Legal") {
                        setting {
                            menu {
                                key = ""
                                title { literal("Legal") }
                                summary { literal("") }
                                default = Unit
                            }
                        }
                        content {

                        }
                    }
                }
            }
        }
    }

    val settings
        get() = _settings!!

    val lightMode
        get() = settings.all.map { it.data }.find { it.key === SettingThemeSelector.themeSelectorKey }!!.state.map { SettingThemeSelector.Mode.valueOf(it as String) === SettingThemeSelector.Mode.LIGHT }
}