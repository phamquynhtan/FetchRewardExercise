package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.and
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest

import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.lvm_layout.*
import java.util.Collections.list
import java.util.Collections.sort


class MainActivity : AppCompatActivity() {

    lateinit var listRV :RecyclerView
    private lateinit var loadingBar : ProgressBar
    lateinit var listAdapter: ListAdapter
    lateinit var list : ArrayList<ListViewModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listRV = findViewById(R.id.listRV)
        loadingBar = findViewById(R.id.loadingBar)

        list = ArrayList()

        listAdapter = ListAdapter(list)
        listRV.adapter = listAdapter

        getAllItem()




    }

    @SuppressLint("SuspiciousIndentation")
    private fun getAllItem() {
        val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        val queue = Volley.newRequestQueue(this@MainActivity)
        val request= JsonArrayRequest(Request.Method.GET, url , null, { response ->
            loadingBar.visibility = View.GONE

            try {
                for (i in 0 until response.length() ) {
                    val responseObj = response.getJSONObject(i)
                    val listID = responseObj.getInt("listId")
                    val id = responseObj.getInt("id")
                    val name = responseObj.getString("name")

                            list.add(ListViewModel(id, listID, name))

                    //sorted list by list id and then name

                    list.sortBy { it.name}
                    list.sortBy { it.listId }

//filter out name contend "null" and ""
                       list.sortBy { it.name == "" }
                        list.sortBy { it.name == "null" }



                    listAdapter.notifyDataSetChanged()

                }


              

                // on below line we
                // are handling exception
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, { _ ->
            // in this case we are simply displaying a toast message.
            Toast.makeText(this@MainActivity, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        })
        // at last we are adding our
        // request to our queue.
        queue.add(request)
    }
}





