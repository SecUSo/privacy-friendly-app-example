package org.secuso.privacyfriendlyexample.ui

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_help.*

import org.secuso.privacyfriendlyexample.R

import org.secuso.privacyfriendlyexample.ui.adapter.ExpandableListAdapter
import java.util.*
import kotlin.collections.LinkedHashMap


/**
 * Class structure taken from tutorial at http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 * last access 27th October 2016
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 */
class HelpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val expandableListDetail = buildData()
        val expandableListTitleGeneral = expandableListDetail.keys.toList()

        generalExpandableListView.setAdapter(ExpandableListAdapter(this, expandableListTitleGeneral, expandableListDetail))

        overridePendingTransition(0, 0)
    }

    /**
     * ID of the menu item it belongs to
     */
    override val navigationDrawerID: Int = R.id.nav_help

    private fun buildData(): LinkedHashMap<String, List<String>> {
        val expandableListDetail = LinkedHashMap<String, List<String>>()

        expandableListDetail[getString(R.string.help_whatis)] = Collections.singletonList(getString(R.string.help_whatis_answer))
        expandableListDetail[getString(R.string.help_feature_one)] = Collections.singletonList(getString(R.string.help_feature_one_answer))
        expandableListDetail[getString(R.string.help_privacy)] = Collections.singletonList(getString(R.string.help_privacy_answer))
        expandableListDetail[getString(R.string.help_permission)] = Collections.singletonList(getString(R.string.help_permission_answer))

        return expandableListDetail
    }
}
