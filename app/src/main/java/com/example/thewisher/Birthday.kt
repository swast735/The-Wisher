package com.example.thewisher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.FragmentBirthdayBinding
import com.google.firebase.storage.FirebaseStorage
import java.util.EnumSet.range


class Birthday : Fragment() {
    val strg:FirebaseStorage=FirebaseStorage.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pics=strg.reference.child("Birthday")
        val bind:FragmentBirthdayBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_birthday, container, false)

        return bind.root
    }
}