package com.example.thewisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.thewisher.databinding.FragmentWeddingBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class Wedding : Fragment() {
    val database = Firebase.database("https://the-wisher-default-rtdb.asia-southeast1.firebasedatabase.app/")
    val myRef = database.getReference("wedding")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind:FragmentWeddingBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_wedding, container, false)
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()) {
                    var j=0
                    val len:Int=snapshot.childrenCount.toInt()
                    val l= arrayListOf<String>()
                    Log.d("msg", snapshot.value.toString().plus(snapshot.key))
                    for (i in snapshot.children) {
                        l.add(i.value.toString())
                    }
                    Glide.with(this@Wedding).load(l[0]).into(bind.elems)
                    bind.next.setOnClickListener {
                        try {
                            Glide.with(this@Wedding).load(l[++j]).into(bind.elems)
                        }catch(e:Exception){
                            if (j > len) {
                                j = len-1
                                Glide.with(this@Wedding).load(l[j]).into(bind.elems)
                            }
                        }
                    }
                    bind.prev.setOnClickListener {
                        try {
                            Glide.with(this@Wedding).load(l[--j]).into(bind.elems)
                        }catch(e:Exception){
                            if (j <= -1) {
                                j = 0
                                Glide.with(this@Wedding).load(l[j]).into(bind.elems)
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        return bind.root
    }


}