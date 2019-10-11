package org.secuso.privacyfriendlyexample.ui

import android.os.Bundle

import kotlinx.android.synthetic.main.activity_help_content.*

import org.secuso.privacyfriendlyexample.ui.adapter.ExpandableListAdapter
import org.secuso.privacyfriendlyexample.ui.helper.HelpDataDump
import org.secuso.privacyfriendlyexample.R

/**
 * Class structure taken from tutorial at http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 * last access 27th October 2016
 * @author Christopher Beckmann (Kamuno), Karola Marky (yonjuni)
 */
class HelpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val expandableListAdapter: ExpandableListAdapter

        val expandableListDetail = HelpDataDump(this).dataGeneral
        val expandableListTitleGeneral = expandableListDetail.keys.toList()

        expandableListAdapter = ExpandableListAdapter(this, expandableListTitleGeneral, expandableListDetail)
        generalExpandableListView.setAdapter(expandableListAdapter)

        overridePendingTransition(0, 0)
    }

    /**
     * ID of the menu item it belongs to
     */
    override val navigationDrawerID: Int = R.id.nav_help
}
