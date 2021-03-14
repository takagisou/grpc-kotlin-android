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
        model.message.observe(this) { message ->
            binding.text.text = message
        }

        // https://grpcb.in/
        // https://github.com/moul/pb/blob/master/grpcbin/grpcbin.proto
//        model.endpoints.observe(this) { endpoints ->
//            binding.text.text = "$endpoints"
//        }
        model.fetch()
    }
}