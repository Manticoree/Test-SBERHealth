package com.app.test_sberhealth.mvp.drugslistfragment.callback

import java.io.Serializable


interface ClickDrugListener : Serializable {
    fun showFullDesc(title: String)
}