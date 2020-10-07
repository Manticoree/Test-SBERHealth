package com.app.test_sberhealth.mvp.drugslistfragment.ageadapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.test_sberhealth.R

class AgeAdapter(fm: FragmentManager, behaviour: Int) : FragmentPagerAdapter(fm, behaviour) {
    companion object {
        const val PAGE_COUNT = 2
        val tabTitles: Array<Int> = arrayOf(R.string.Adult, R.string.Child)
    }

    override fun getItem(position: Int): Fragment {
        if (position == 1) {

        } else {

        }
    }

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position].toString()
}