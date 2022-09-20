package com.test.mornhouse.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.mornhouse.R
import com.test.mornhouse.extension.navigateTo
import com.test.mornhouse.ui.main.fragment.GetFactFragment

class MainActivity : AppCompatActivity(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateTo(
            R.id.fragment_container,
            GetFactFragment(),
            byFade = true
        )
    }
}








