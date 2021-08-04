package com.example.upacket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlincombat.R

/**
 * Create by lxx
 * Date : 2020/12/22 16:53
 * Use by
 */
class TestDemo : AppCompatActivity() {

    private lateinit var netViewModel: NetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        netViewModel = ViewModelProviders.of(this).get(NetViewModel::class.java)
        netViewModel.getFictions()


        netViewModel.fictions.observe(this, Observer {

        })
    }
}