package com.example.unitconverter.View


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.unitconverter.Data.UnitRepo
import com.example.unitconverter.R
import com.example.unitconverter.ViewModel.MainViewModel
import java.lang.reflect.Field
import java.math.BigDecimal


class ConverterActivity : AppCompatActivity() {

    var unitGroupResArr = R.array.distanceUnits
    lateinit var mainViewModel: MainViewModel

    //lateinit var utility: Utility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.fragment_keyboard, KeyboardFragment(), "Keyboard fragment")
            .commit()

        unitGroupResArr = getIntent().getIntExtra("unitGroupName", 0)

        val unitArrSpinner = resources.getStringArray(unitGroupResArr)

        val spinner = findViewById<Spinner>(R.id.spinnerInput)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, unitArrSpinner
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {

                    mainViewModel.unitFrom.value = UnitRepo.getById(getStringResourceId(unitArrSpinner[position] ))

                /*Toast.makeText(this@ConverterActivity,
                            getString(R.string.selected_item) + " " +
                                    "" + units[position], Toast.LENGTH_SHORT).show()*/
                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val spinner2 = findViewById<Spinner>(R.id.spinnerOutput)
        if (spinner2 != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, unitArrSpinner
            )
            spinner2.adapter = adapter

            spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    mainViewModel.unitTo.value = UnitRepo.getById(getStringResourceId(unitArrSpinner[position] ))

                    /*Toast.makeText(
                        this@ConverterActivity,
                        getString(R.string.selected_item) + " " +
                                "" + unitArrSpinner[position], Toast.LENGTH_SHORT
                    ).show()*/
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val editText = findViewById<EditText>(R.id.editTextInputDecimal)
        mainViewModel.valueFrom.value = BigDecimal(editText.text.toString())

        val textView = findViewById<TextView>(R.id.textViewOutputDecimal)
        mainViewModel.valueTo.value = BigDecimal(textView.text.toString())

    }
    //TODO: where to place this function??????????

    fun getStringResourceId(stringToSearch: String): Int {
        val fields: Array<Field> = R.string::class.java.fields
        for (field in fields) {
            val id = field.getInt(field)
            val str = resources.getString(id)
            if (str == stringToSearch) {
                return id
            }
        }
        return -1
    }
}


    //private fun ConvertFromStringToNumber()