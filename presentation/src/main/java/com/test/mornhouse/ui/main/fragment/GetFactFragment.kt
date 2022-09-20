package com.test.mornhouse.ui.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.chad.library.adapter.base.diff.BaseQuickDiffCallback
import com.test.domain.model.Result
import com.test.mornhouse.R
import com.test.mornhouse.databinding.FragmentGetFactBinding
import com.test.mornhouse.extension.navigateTo
import com.test.mornhouse.extension.setOnSafeClickListener
import com.test.mornhouse.model.Fact
import com.test.mornhouse.ui.main.MainActivity
import com.test.mornhouse.ui.main.adapter.FactAdapter
import com.test.mornhouse.viemodel.FactNumberViewModel
import kotlinx.android.synthetic.main.fragment_get_fact.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class GetFactFragment : Fragment(R.layout.fragment_get_fact), TextWatcher{

    private val factNumberViewModel: FactNumberViewModel by viewModel()
    private val factAdapter: FactAdapter by inject()
    private var fragmentGetFactBinding: FragmentGetFactBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentGetFactBinding.bind(view)
        fragmentGetFactBinding = binding
        binding.viewmodel = factNumberViewModel
        initViews()
        initObservers()
        initListeners()
    }

    private fun initViews(){
        rv_facts_fragment_get_fact.adapter = factAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers(){
        factNumberViewModel.factLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                factAdapter.setNewData(it)
                MainActivity().runOnUiThread {
                    kotlinx.coroutines.Runnable {
                        factAdapter.notifyDataSetChanged()
                    }
                }
            }
        })
        factNumberViewModel.getFactNumber.observe(viewLifecycleOwner, Observer {result->
            when(result){
                is Result.Success ->{
                    factNumberViewModel.insertFact(Fact(text = result.data.toString()))
                    factNumberViewModel.getFacts()
                }
                is Result.Error -> {
                    Toast.makeText(context, "Ups, error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initListeners(){
        fragmentGetFactBinding?.etGetNumberFragmentGetFact?.addTextChangedListener(this)
        factAdapter.onItemClickListener = { position, _ ->
            val fact = factAdapter.getFact(position)
            navigateTo(R.id.fragment_container, DetailFactFragment(), args = Bundle().apply {
                putParcelable("fact", fact)
            }, backStackTag = DetailFactFragment::class.simpleName)
        }
    }


    override fun onResume() {
        super.onResume()
        factNumberViewModel.getFacts()
    }

    private fun validateFields() {
        fragmentGetFactBinding?.btnGetFactFragmentGetFact?.isEnabled = listOf(
            fragmentGetFactBinding?.etGetNumberFragmentGetFact?.text!!.isNotEmpty()
        ).all { it }
    }

    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        validateFields()
    }
}