package com.example.testcrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testcrap.Outside
import com.example.testcrap.Outside.Inside
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk23.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        mInside = object: Inside() {
            override fun setTemperature(temp: Int) {
                super.setTemperature(temp)
            }
        }
        */
        //tvOutput = findViewById(R.id.tvOutput)

        btnGet.onClick {
            Log.d(TAG, "In GET button click")

            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this@MainActivity)
            url = etUrl.text.toString()

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    val responseLen = response.length
                    tvOutput.text = "Response is:\n${response.substring(0, if (responseLen > 500) 500 else responseLen)}"
                },
                Response.ErrorListener { tvOutput.text = "That didn't work!" })

// Add the request to the RequestQueue.
            queue.add(stringRequest)

        }


    }

    /*
    var mOutside: Outside? = null
    var mInside: Inside? = null
    */

    private lateinit var url: String

    companion object {
        private val TAG: String? = MainActivity::class.simpleName
    }
}
