package com.example.testcrap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testcrap.Outside
import com.example.testcrap.Outside.Inside
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk23.coroutines.onClick
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private fun partialResponse(bigStr: String): String {
        val responseLen = bigStr.length
        return bigStr.substring(0, if (responseLen > 500) 500 else responseLen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = RequestQueueSingleton.getInstance(applicationContext).requestQueue
        /*
        mInside = object: Inside() {
            override fun setTemperature(temp: Int) {
                super.setTemperature(temp)
            }
        }
        */
        //tvOutput = findViewById(R.id.tvOutput)
        etUrl.setText(url)



        btnGet.onClick {
            Log.d(TAG, "In GET button click")

            // Instantiate the RequestQueue.
            url = etUrl.text.toString()

            val stringRequest = StringRequest(
                Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    tvOutput.text = "Response is:\n${partialResponse(response)}"
                },
                Response.ErrorListener { tvOutput.text = "That didn't work!" })

// Add the request to the RequestQueue.
            requestQueue.add(stringRequest)

        }

        btnPost.onClick {
            var json: JSONObject = JSONObject()
            json.put("Param1", "value for param1")
            json.put("Param2", "value for param2")
            json.put("intParam", 23)
            json.put("boolParam", false)
            url = etUrl.text.toString()
            var jsObReq: JsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                json,

                object: Response.Listener<JSONObject> {
                    override fun onResponse(response: JSONObject?) {
                        tvOutput.text = "Response is:\n$response"
                    }

                },
                Response.ErrorListener { error ->
                    tvOutput.text = "Error on JsonObjectRequest:\n${error}"
                }
            ) /*{
                override fun getParams(): MutableMap<String, String>? {
                    return hashMapOf(Pair("hwId", "abcdefg"))
                }
            }*/
            requestQueue.add(jsObReq)
        }

    }

    /*
    var mOutside: Outside? = null
    var mInside: Inside? = null
    */

    private var url: String = "http://192.168.1.20:8080/Echo/EchoServlet"
    private lateinit var requestQueue: RequestQueue

    companion object {
        private val TAG: String? = MainActivity::class.simpleName
    }
}
