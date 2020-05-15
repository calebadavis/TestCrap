package com.example.testcrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testcrap.Outside

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mInside = object: Outside.Inside() {
            override fun setTemperature(temp: Int) {
                super.setTemperature(temp)
            }
        }
    }
    var mOutside: Outside? = null
    var mInside: Outside.Inside? = null
}
