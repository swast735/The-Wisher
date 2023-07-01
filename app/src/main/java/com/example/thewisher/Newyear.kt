package com.example.thewisher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.FragmentNewyearBinding

class Newyear : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind:FragmentNewyearBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_newyear, container, false)
        return  bind.root
    }

}