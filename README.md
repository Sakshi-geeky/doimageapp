DOG IMAGE APP

Dog Image App is an Android application built using Kotlin that allows users to fetch random dog images from the internet. The app utilizes the DogImageFetcher library to communicate with the Dog CEO API and provides an easy-to-use interface to view random dog images.

Features
Fetch Single Dog Image: Users can fetch a single random dog image and view it in the app.
Fetch Multiple Dog Images: Users can input a number (between 1 and 10) to fetch that many dog images and display them in a list.
DogImageFetcher Library
The app uses the DogImageFetcher library, which provides two main methods for fetching dog images:

getImage(): Fetches a single random dog image.
getImages(int number): Fetches the specified number of random dog images.
How to Use the App
Main Activity
"Next" Button: Click the button to fetch a single random dog image. The image will be displayed in the app.
Number Input and "Submit" Button: Input a number between 1 and 10, and press the "Submit" button to fetch that many random dog images. The fetched images will be displayed in a list on a new activity.
Screenshots
Main Screen: Shows a random dog image.
Image List Screen: Displays multiple fetched dog images in a list.
Getting Started
Prerequisites
Android Studio: You need to have Android Studio installed to build and run the app.
Kotlin Support: The app is written in Kotlin, and you must have Kotlin support enabled in Android Studio.

Sync Dependencies:

Ensure you have an active internet connection and click Sync Now to download all required dependencies.
Run the App:

Connect an Android device or start an emulator.
Click on the Run button in Android Studio.
Dependencies
The following dependencies are used in the project:

Retrofit: For making network requests to fetch images.
groovy
Copy code
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
Room Database: For storing fetched images locally.
groovy
Copy code
implementation "androidx.room:room-runtime:2.4.2"
kapt "androidx.room:room-compiler:2.4.2"
Glide: For loading images from URLs into ImageViews.
groovy
Copy code
implementation 'com.github.bumptech.glide:glide:4.12.0'
kapt 'com.github.bumptech.glide:compiler:4.12.0'
Kotlin Coroutines: For handling asynchronous operations.
groovy
Copy code
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
Usage of DogImageFetcher
To use the DogImageFetcher in the app, initialize it with a Context:

kotlin
Copy code
val dogImageFetcher = DogImageFetcher(context)
Fetch a Single Image:

Use the getImage() method to get a random dog image. Since it is a suspend function, call it inside a coroutine:

kotlin
Copy code
lifecycleScope.launch {
    val imageUrl = dogImageFetcher.getImage()
    // Load the image using Glide or Picasso
    Glide.with(this@MainActivity).load(imageUrl).into(dogImageView)
}
Fetch Multiple Images:

Use the getImages(number: Int) method to fetch multiple dog images:

kotlin
Copy code
lifecycleScope.launch {
    val imageUrls = dogImageFetcher.getImages(5) // Fetches 5 images
    val intent = Intent(this@MainActivity, ImageListActivity::class.java)
    intent.putStringArrayListExtra("dogImages", ArrayList(imageUrls))
    startActivity(intent)
}
Example Code
Below is an example code snippet demonstrating how the getImage() and getImages() methods are used in the MainActivity:

kotlin
Copy code
class MainActivity : ComponentActivity() {
    private lateinit var dogImageView: ImageView
    private lateinit var dogImageFetcher: DogImageFetcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogImageFetcher = DogImageFetcher(this)
        dogImageView = findViewById(R.id.dogImageView)
        val nextButton: Button = findViewById(R.id.nextButton)
        val submitButton: Button = findViewById(R.id.submitButton)
        val numberInput: EditText = findViewById(R.id.numberInput)

        nextButton.setOnClickListener {
            lifecycleScope.launch {
                val imageUrl = dogImageFetcher.getImage()
                Glide.with(this@MainActivity).load(imageUrl).into(dogImageView)
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
    }
}
API Information
This app uses the Dog CEO API to fetch random images of dogs.

API Endpoint: https://dog.ceo/api/breeds/image/random
Sample JSON Response:
json
Copy code
{
  "message": "https://images.dog.ceo/breeds/leonberg/n02111129_2785.jpg",
  "status": "success"
}
Screens
Main Screen: Displays a random dog image.
Image List Screen: Displays a list of fetched dog images in a RecyclerView.
