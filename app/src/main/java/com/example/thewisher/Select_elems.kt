package com.example.thewisher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Select_elems : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var  quote:EditText
    lateinit var nxtBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_elems)
        name=findViewById(R.id.name)
        quote=findViewById(R.id.quote)
        nxtBtn=findViewById(R.id.btnNxt)
        val strName=name.text.toString()
        val strQt=quote.text.toString()
        nxtBtn.setOnClickListener {
            val i=Intent(this@Select_elems,Des::class.java)
            i.putExtra("name",strName)
            i.putExtra("quote",strQt)
            startActivity(i)
        }
    }
}