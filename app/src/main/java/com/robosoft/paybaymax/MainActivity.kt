package com.robosoft.paybaymax

import ApiHelper
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.robosoft.paybaymax.utils.Status
import com.robosoft.paybaymax.data.api.RetrofitBuilder
import com.robosoft.paybaymax.viewmodel.QuotesViewModel
import com.robosoft.paybaymax.viewmodel.ViewModelFactory

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: QuotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.API_INTERFACE))
        ).get(QuotesViewModel::class.java)


        viewModel.getQuotes().observe(this, Observer {
            it?.let { resource ->
                Log.e("data", resource.toString());
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data-> Log.e("Success",data.toString())
                            if(data.success) {
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


}
