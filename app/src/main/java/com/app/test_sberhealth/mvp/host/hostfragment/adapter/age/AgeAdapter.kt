package com.app.test_sberhealth.mvp.host.hostfragment.adapter.age

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.test_sberhealth.mvp.show.list.ShowFragment


class AgeAdapter(
    fm: FragmentManager,
    behaviour: Int
) :
    FragmentPagerAdapter(fm, behaviour) {

    companion object {
        const val PAGE_COUNT = 2
        private const val ADULT = "Взрослые"
        private const val CHILD = "Детские"
        val tabTitles: Array<String> = arrayOf(
            ADULT,
            CHILD
        )
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 1) {
            ShowFragment.newInstance(1)
        } else {
            ShowFragment.newInstance(2)
        }
    }

    override fun getCount(): Int =
        PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position]

}