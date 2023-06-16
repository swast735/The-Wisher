package com.example.thewisher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.thewisher.databinding.FragmentOcassionBinding


class Ocassion : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind:FragmentOcassionBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_ocassion, container, false)
        bind.bday.setOnClickListener{
            Toast.makeText(requireContext(), "Birthday, Loading", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it)
        }
        bind.anniday.setOnClickListener{
            Toast.makeText(requireContext(), "Anniversary, Loading", Toast.LENGTH_SHORT).show()
        }
        bind.wedday.setOnClickListener{
            Toast.makeText(requireContext(), "Wedding, Loading", Toast.LENGTH_SHORT).show()
        }
        bind.NYay.setOnClickListener{
            Toast.makeText(requireContext(), "New Year, Loading", Toast.LENGTH_SHORT).show()
        }
        return bind.root
    }
}