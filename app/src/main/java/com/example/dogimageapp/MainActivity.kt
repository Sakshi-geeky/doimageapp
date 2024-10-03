package com.example.dogimageapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.dogimagelibrary.DogImageFetcher
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var dogImageView: ImageView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var numberInput: EditText
    private lateinit var submitButton: Button

    private lateinit var dogImageFetcher: DogImageFetcher
    private var currentIndex = -1
    private val fetchedImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImageView = findViewById(R.id.dogImageView)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)
        numberInput = findViewById(R.id.numberInput)
        submitButton = findViewById(R.id.submitButton)

        dogImageFetcher = DogImageFetcher(this)

        nextButton.setOnClickListener {
            lifecycleScope.launch {
                val imageUrl = dogImageFetcher.getImage()
                fetchedImages.add(imageUrl)
                currentIndex++
                displayImage(imageUrl)
                previousButton.isEnabled = currentIndex > 0
            }
        }

        submitButton.setOnClickListener {
            val number = numberInput.text.toString().toIntOrNull()
            if (number != null && number in 1..10) {
                lifecycleScope.launch {
                    val imageUrls = dogImageFetcher.getImages(number)
                    val intent = Intent(this@MainActivity, ImageListActivity::class.java)
                    intent.putStringArrayListExtra("dogImages", ArrayList(imageUrls))
                    startActivity(intent)
                }
            }
        }

        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                displayImage(fetchedImages[currentIndex])
                previousButton.isEnabled = currentIndex > 0
            }
        }

        previousButton.isEnabled = false
    }

    private fun displayImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(dogImageView)
    }
}
