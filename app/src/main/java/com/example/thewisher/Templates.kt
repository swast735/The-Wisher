package com.example.thewisher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.FragmentTemplatesBinding


class Templates : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind:FragmentTemplatesBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_templates,container,false)
        return bind.root
    }

}
