package com.example.thewisher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.FragmentOcassionBinding


class Ocassion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind:FragmentOcassionBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_ocassion, container, false)
        return bind.root
    }
}