package com.test.mornhouse.ui.main.fragment

import androidx.fragment.app.Fragment
import com.test.mornhouse.R
import com.test.mornhouse.model.Fact
import kotlinx.android.synthetic.main.fragment_details_fact.*

class DetailFactFragment : Fragment(R.layout.fragment_details_fact) {

    private var fact: Fact? = null

    override fun onResume() {
        super.onResume()
        if (arguments?.getParcelable<Fact>("fact") != null) {
            fact = arguments?.getParcelable<Fact>("fact")
            tv_text_number_fragment_details_fact.text = fact?.text
        }
    }
}