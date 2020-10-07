package com.app.test_sberhealth.mvp.drugslistfragment

import com.app.test_sberhealth.adapter.DrugsAdapter

interface DrugsListFragmentContract {

    interface View {
        fun showDrugsFragment(
            listAdult: MutableList<DrugsAdapter>,
            listChild: MutableList<DrugsAdapter>
        )

        fun showErrorRepeat()
    }

    interface Presenter {
        fun getDrugs()
    }

}