package com.app.test_sberhealth.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.test_sberhealth.R
import com.app.test_sberhealth.base.BaseFragment

import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment : BaseFragment() {

    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs =
                DescriptionFragmentArgs.fromBundle(
                    it
                )
            title = safeArgs.title
        }
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_description, container, false)

    override fun onStart() {
        super.onStart()
        mtvTitleDrugs.text = title
    }

}