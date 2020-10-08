package com.app.test_sberhealth.mvp.drugslistfragment

import com.app.test_sberhealth.entities.DrugItem

interface DrugsListFragmentContract {

    interface View {
        fun showDrugsFragment(
            listAdult: MutableList<DrugItem>,
            listChild: MutableList<DrugItem>
        )

        fun showErrorRepeat()
        fun moveToSearch()
    }

    interface Presenter {
        fun getDrugs()
        fun onMoveToSearch()
    }

}