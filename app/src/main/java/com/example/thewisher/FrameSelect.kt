package com.example.thewisher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.ActivityFrameSelectBinding

class FrameSelect : AppCompatActivity() {
    lateinit var bind:ActivityFrameSelectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_frame_select)
    }
}
