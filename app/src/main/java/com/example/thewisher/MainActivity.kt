package com.example.thewisher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.thewisher.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private  lateinit var bind: ActivityMainBinding
    lateinit var fauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val move_up: Animation = AnimationUtils.loadAnimation(this,R.anim.slide_up)
        fauth=FirebaseAuth.getInstance()
        val uid=fauth.currentUser
        bind=DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        bind.fragment.setOnClickListener {
            if(uid==null) {
                val dia = SignUp()
                dia.show(supportFragmentManager, dia.tag)
            }
            else{
                startActivity(Intent(this@MainActivity, FrameSelect::class.java))
            }
        }
    }
}