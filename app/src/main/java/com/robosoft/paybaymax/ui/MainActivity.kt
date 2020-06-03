package com.robosoft.paybaymax.ui

import ApiHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.robosoft.paybaymax.R
import com.robosoft.paybaymax.data.api.RetrofitBuilder
import com.robosoft.paybaymax.data.model.Quotes
import com.robosoft.paybaymax.ui.adapter.RatesAdapter
import com.robosoft.paybaymax.utils.Status
import com.robosoft.paybaymax.viewmodel.QuotesViewModel
import com.robosoft.paybaymax.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var spinner: Spinner
    private lateinit var editText: EditText
    private lateinit var viewModel: QuotesViewModel
    private lateinit var adapter: RatesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        setSupportActionBar(toolbar);
        setupUI()

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.API_INTERFACE))
        ).get(QuotesViewModel::class.java)


        viewModel.getCurriencies().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data->
                            if(data.success) {
                                val abbreviation: ArrayList<String> =ArrayList(data.currencies.keys)
                                  val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, abbreviation)
                                  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                  spinner.adapter = adapter
                            }
                            else {
                                Toast.makeText(this, data.error.type +" "+data.error.info, Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })

    }

    private fun setupUI() {
        spinner = findViewById(R.id.spinner)
        editText = findViewById(R.id.editText)
        spinner.onItemSelectedListener = this
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        adapter = RatesAdapter(arrayListOf(), isEmptyEdt())
        recyclerView.adapter = adapter
    }

     fun isEmptyEdt(): String {
        val edtFieldstr = editText.text.toString()
        if(edtFieldstr.isEmpty()){
            return "1";
        }else return editText.text.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
           val selected = parent.getItemAtPosition(position)
            viewModel.getQuotes(selected.toString()).observe(this, Observer {
                it?.let { resource ->
                    Log.e("data", resource.toString());
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let { data-> Log.d("Success",data.toString())
                                if(data.success) {
                                    val aa : HashMap<String,Double> = data.quotes;
                                    var arrayList : ArrayList<Quotes> = ArrayList()
                                    for(i in aa.entries ){
                                        val a = Quotes(i.key,i.value)
                                        arrayList.add(a)
                                    }
                                    exchangeRates(arrayList)
                                }
                                else {
                                Toast.makeText(this, data.error.type +" "+data.error.info, Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })

        };
    }

    private fun exchangeRates(quotes: List<Quotes>) {
        adapter.apply {
            addQuotes(quotes, isEmptyEdt())
            notifyDataSetChanged()
        }
    }


}
