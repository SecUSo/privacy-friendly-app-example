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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import org.secuso.pfacore.application.PFApplication
import org.secuso.pfacore.model.DrawerElement
import org.secuso.privacyfriendlyexample.R
import org.secuso.privacyfriendlyexample.databinding.ActivityGameBinding

/**
 * This activity is an example for the main menu of gaming applications
 * @author Christopher Beckmann (Kamuno)
 * @version 20161225
 */
class GameActivity : BaseActivity() {

    /**
     * ID of the menu item it belongs to
     */
    override val navigationDrawerID: Int = R.id.nav_game

    @Composable
    override fun Content(application: PFApplication) {
        val context = LocalContext.current as FragmentActivity
        AndroidViewBinding(ActivityGameBinding::inflate) {
            val adapter = SectionsPagerAdapter(context.supportFragmentManager)
            chooseGameTypeViewPager.adapter = adapter
            val index = mSharedPreferences.getInt("lastChosenPage", 0)

            chooseGameTypeViewPager.currentItem = index

            //care for initial postiton of the ViewPager
            arrowLeft.visibility = if (index == 0) View.INVISIBLE else View.VISIBLE
            arrowRight.visibility = if (index == adapter.count - 1) View.INVISIBLE else View.VISIBLE

            arrowLeft.setOnClickListener { chooseGameTypeViewPager.arrowScroll(View.FOCUS_LEFT) }
            arrowRight.setOnClickListener { chooseGameTypeViewPager.arrowScroll(View.FOCUS_RIGHT) }

            //Update ViewPager on change
            chooseGameTypeViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    arrowLeft.visibility = if (position == 0) View.INVISIBLE else View.VISIBLE
                    arrowRight.visibility = if (position == adapter.count - 1) View.INVISIBLE else View.VISIBLE

                    //save position in settings
                    val editor = mSharedPreferences.edit()
                    editor.putInt("lastChosenPage", position)
                    editor.apply()
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    }

    override fun isActiveDrawerElement(element: DrawerElement): Boolean {
        return element.name == getString(R.string.action_game)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PageFragment (defined as a static inner class below).
            return PageFragment.newInstance(position)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    class PageFragment : Fragment() {


        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            var id = 0
            if (arguments != null) {
                id = arguments!!.getInt(ARG_SECTION_NUMBER)
            }

            val rootView = inflater.inflate(R.layout.fragment_game_mode, container, false)

            val textView = rootView.findViewById<View>(R.id.section_label) as TextView
            textView.text = "Mode: $id"
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PageFragment {
                val fragment = PageFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
