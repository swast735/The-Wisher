package com.example.thewisher

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale





class Des : AppCompatActivity(), View.OnTouchListener {
    lateinit var temp:ImageView
    lateinit var name:CardView
    lateinit var name1:EditText
    lateinit var quote:EditText
    lateinit var lay:RelativeLayout
    lateinit var pics:ImageView
    lateinit var buttonSave:Button

    fun saveLayoutAsImage(context: Context, layout: View, fileName: String): Uri? {
        // Create a bitmap from the layout
        val bitmap = viewToBitmap(layout)

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), fileName)
        try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
        } catch (e: IOException) {
            return null
        }
        val contentResolver = context.contentResolver
        val uri = Uri.fromFile(file)
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, fileName)
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
    private fun viewToBitmap(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        view.isDrawingCacheEnabled = false
        return bitmap
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_des)
        pics=findViewById(R.id.dp)
        temp = findViewById(R.id.temp)
        temp.setOnClickListener {
            name1.clearFocus()
            quote.clearFocus()
        }
        name1 = findViewById(R.id.strdName)
        intent.getStringExtra("name").toString()
        intent.getStringExtra("quote").toString()
        quote = findViewById(R.id.strdQuote)
        lay=findViewById(R.id.rtop)
        Glide.with(this).load(intent.getStringExtra("link")).into(temp)
        name1.setOnTouchListener(this)
        quote.setOnTouchListener(this)
        pics.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            startActivityForResult(i, 1)
        }
            pics.setOnTouchListener(this@Des)
        buttonSave = findViewById(R.id.button_save)
        buttonSave.visibility=View.VISIBLE
        buttonSave.setOnClickListener {
            it.visibility=View.INVISIBLE
            val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US)
            val now = Date()
            val fileName = "my_layout${formatter.format(now)}.jpg"
            val uri = saveLayoutAsImage(this, lay, fileName)
            Toast.makeText(this, "Layout saved as image with URI: $fileName", Toast.LENGTH_LONG).show()
            it.visibility=View.VISIBLE
        }
    }
    var x:Float=0.0f
    var y:Float=0.0f
    var dX:Float=0.0f
    var dY:Float=0.0f
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event != null) {
            when(event.action){
                MotionEvent.ACTION_DOWN->{
                    x=event.x
                    y=event.y
                }
                MotionEvent.ACTION_MOVE->{
                    dX=event.x-x
                    dY=event.y-y

                    if (v != null) {
                        v.x=v.x+dX
                        v.y=v.y+dY
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            when (requestCode) {
                1 -> if (resultCode == RESULT_OK) {
                    val url=data?.data
                    Glide.with(this).load(url).into(pics)
                } else if (resultCode == RESULT_CANCELED) {
                    Log.e("tag", "Selecting picture cancelled")
                }
            }
        } catch (e: Exception) {
            Log.e("tag", "Exception in onActivityResult : " + e.message)
        }
    }
}