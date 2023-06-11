package com.example.thewisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.thewisher.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind=DataBindingUtil.inflate<FragmentWelcomeBinding>(inflater,R.layout.fragment_welcome,container,false)
        val move:Animation=AnimationUtils.loadAnimation(this.context, R.anim.fade_out)
//        val move_up:Animation=AnimationUtils.loadAnimation(this.context,R.anim.slide_up)
//        val move_down:Animation=AnimationUtils.loadAnimation(this.context,R.anim.slide_down)
        bind.wlcmTxt.startAnimation(move)
        return bind.root
    }
}
