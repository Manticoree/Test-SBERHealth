package com.app.test_sberhealth.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrugItem(
    val id: Int,
    val title: String,
    val icon: String,
    val isReadyForKids: Boolean
) : Parcelable