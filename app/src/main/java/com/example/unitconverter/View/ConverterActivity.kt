package com.example.unitconverter.View


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
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
        val view = setContentView(R.layout.activity_converter)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.fragment_keyboard, KeyboardFragment(), "Keyboard fragment")
            .commit()

        unitGroupResArr = intent.getIntExtra("unitGroupName", 0)

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
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    mainViewModel.setUnitFrom(UnitRepo.getById(getStringResourceId(unitArrSpinner[position])))

                    /*Toast.makeText(this@ConverterActivity,
                            getString(R.string.selected_item) + " " +
                                    "" + units[position], Toast.LENGTH_SHORT).show()*/
                }


                override fun onNothingSelected(parent: AdapterView<*>) {
                    //Log.d("debug rotat error", "inside nothing selected")
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
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    mainViewModel.setUnitTo(UnitRepo.getById(getStringResourceId(unitArrSpinner[position])))

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
        val textView = findViewById<View>(R.id.textViewOutputDecimal) as TextView

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                mainViewModel.convert()

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
               // var str =
               // var bd = BigDecimal.ZERO
                //if(str != "")
                //    bd = .toBigDecimal()

                mainViewModel.setValueFrom(s.toString().toBigDecimal())
            }
        })
        mainViewModel.valueTo.observe(this, Observer {
            if(editText.text.toString() == "") {
                textView.text = ""
            }
            else {
                textView.text = it.toPlainString()
            }
        })
    }


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


