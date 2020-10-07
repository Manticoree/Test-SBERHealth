package com.app.test_sberhealth.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DrugItem(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("icon") val icon: String,
    @field:SerializedName("isReadyForKids") val isReadyForKids: Boolean
) : Serializable