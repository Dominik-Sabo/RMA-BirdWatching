package com.sabo.dominik.birdwatching

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvCounter: TextView
    lateinit var btnWhite: Button
    lateinit var btnBlack: Button
    lateinit var btnBlue: Button
    lateinit var btnYellow: Button
    lateinit var btnReset: Button
    val counter: Counter = Counter()
    lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        setUpViews()
        setUpClickListeners()
        initiateCounter()
    }

    override fun onPause() {
        saveToSharedPreferences()
        super.onPause()
    }

    override fun onStop() {
        saveToSharedPreferences()
        super.onStop()
    }

    fun initiateCounter(){
        loadFromSharedPreferences()
        tvCounter.text = counter.count.toString()
        tvCounter.setBackgroundColor(counter.backgroundColour)
    }

    fun setUpViews()
    {
        tvCounter = findViewById(R.id.tvCounter)
        btnWhite = findViewById(R.id.btnWhite)
        btnBlack = findViewById(R.id.btnBlack)
        btnBlue = findViewById(R.id.btnBlue)
        btnYellow = findViewById(R.id.btnYellow)
        btnReset = findViewById(R.id.btnReset)
    }

    fun setUpClickListeners()
    {
        btnWhite.setOnClickListener{
            counter.backgroundColour = Color.WHITE
            increment()
        }

        btnBlack.setOnClickListener{
            counter.backgroundColour = Color.BLACK
            increment()
        }

        btnBlue.setOnClickListener{
            counter.backgroundColour = Color.BLUE
            increment()
        }

        btnYellow.setOnClickListener{
            counter.backgroundColour = Color.YELLOW
            increment()
        }

        btnReset.setOnClickListener{
            reset()
        }
    }

    fun reset(){
        counter.backgroundColour = Color.CYAN
        counter.reset()
        tvCounter.text = counter.count.toString()
        tvCounter.setBackgroundColor(counter.backgroundColour)
    }

    fun increment(){
        counter.increment()
        tvCounter.text = counter.count.toString()
        tvCounter.setBackgroundColor(counter.backgroundColour)
    }

    fun saveToSharedPreferences(){
        with (sharedPreferences.edit()) {
            putInt("count", counter.count)
            putInt("colour", counter.backgroundColour)
            commit()
        }
    }

    fun loadFromSharedPreferences(){
        counter.count = sharedPreferences.getInt("count", 0)
        counter.backgroundColour = sharedPreferences.getInt("colour", Color.CYAN)
    }
}
