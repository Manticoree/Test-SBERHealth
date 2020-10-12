package com.app.test_sberhealth.mvp.show.list

import com.app.test_sberhealth.entities.DrugItem

interface ShowContract {

    interface View {
        fun initRecView(initList: MutableList<DrugItem>)
        fun showErrorRepeat()
        fun visibilityElements(visDrugsList: Int, visTextError: Int, visRepeat: Int)
        fun showShimmer()
        fun hideShimmer()
        fun repeat()
        fun clickRepeat()
    }

    interface Presenter {
        fun getDrugs()
    }
}