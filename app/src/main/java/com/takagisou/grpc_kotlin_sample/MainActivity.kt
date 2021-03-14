package com.takagisou.grpc_kotlin_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.github.kittinunf.fuel.httpGet
import com.takagisou.grpc_kotlin_sample.databinding.ActivityMainBinding
import com.takagisou.grpc_kotlin_sample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bind()
    }

    private fun bind() {
        // cf. https://developer.android.com/topic/libraries/architecture/viewmodel#implement
        model.message.observe(this) { message ->
            binding.text.text = message
        }
        model.fetch()

//        "http://10.0.2.2"
//            .httpGet()
//            .responseString { request, response, result ->
//                Log.d("MAIN", "$request")
//                Log.d("MAIN", "$response")
//                Log.d("MAIN", "$result")
//            }
    }
}