package com.app.test_sberhealth.mvp.showdrugsadultfragment

import com.app.test_sberhealth.entities.DrugItem

interface ShowDrugsAdultFragmentContract {

    interface View {
        fun initRecView(initList: MutableList<DrugItem>)
        fun showErrorRepeat()
        fun showShimmer()
        fun hideShimmer()
    }

    interface Presenter {
        fun getDrugs()
    }
}