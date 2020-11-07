package com.kostya_zinoviev.kotlinfishermanhanbook

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContentAvtivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_layout)

        val intent = intent

        if (intent != null) {
            val titleFish:String = intent.getStringExtra("Title")
            val descriptionFish:String = intent.getStringExtra("Description")
            val imageView:Int = intent.getIntExtra("ImageView",R.drawable.ic_android_black_24dp)

            val titleTextView = findViewById<TextView>(R.id.title)
            titleTextView.text = titleFish

            val descriptionTextView = findViewById<TextView>(R.id.description)
            descriptionTextView.text = descriptionFish

            val imageViewFish = findViewById<ImageView>(R.id.imageViewFish)
            imageViewFish.setImageResource(imageView)
        }



    }
}