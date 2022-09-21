package com.example.unitconverter.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.unitconverter.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.fragment_keyboard, KeyboardFragment(), "Keyboard fragment" ).commit()

        val units = resources.getStringArray(R.array.)
        val spinner = findViewById<Spinner>(R.id.spinnerInput)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, units)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@ConverterActivity,
                        getString(R.string.selected_item) + " " +
                                "" + units[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val spinner2 = findViewById<Spinner>(R.id.spinnerOutput)
        if (spinner2 != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, units)
            spinner2.adapter = adapter

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@ConverterActivity,
                        getString(R.string.selected_item) + " " +
                                "" + units[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        Log.d("testlog", "before transaction")

    }
    //private fun ConvertFromStringToNumber()
}