package com.example.thewisher

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.thewisher.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var sp:SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sp= requireActivity().getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        val bind:FragmentHomeBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        bind.name.text=sp.getString("name","")
        return bind.root
    }

}