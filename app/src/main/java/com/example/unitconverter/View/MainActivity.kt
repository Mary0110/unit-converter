package com.example.unitconverter.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.unitconverter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val speedButton = findViewById<Button>(R.id.main_activity_speed_button)
        speedButton.setOnClickListener {
            val intentToConverterScreen = Intent(this, ConverterActivity::class.java)
            intentToConverterScreen.putExtra("unitGroupName", R.array.speedUnits)
            startActivity(intentToConverterScreen)
            Toast.makeText(this@MainActivity, "Yoo clicked speed", Toast.LENGTH_LONG).show()
        }

        val weightButton = findViewById<Button>(R.id.main_activity_weight_button)
        weightButton.setOnClickListener {
            val intentToConverterScreen = Intent(this, ConverterActivity::class.java)
            intentToConverterScreen.putExtra("unitGroupName", R.array.weightUnits)
            startActivity(intentToConverterScreen)
            Toast.makeText(this@MainActivity, "Yoo clicked weight", Toast.LENGTH_LONG).show()
        }

        val distanceButton = findViewById<Button>(R.id.main_activity_distance_button)
        distanceButton.setOnClickListener {
            val intentToConverterScreen = Intent(this, ConverterActivity::class.java)
            intentToConverterScreen.putExtra("unitGroupName", R.array.distanceUnits)
            startActivity(intentToConverterScreen)
            Toast.makeText(this@MainActivity, "Yoo clicked distance", Toast.LENGTH_LONG).show()
        }
    }
}