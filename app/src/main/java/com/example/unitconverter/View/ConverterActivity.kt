package com.example.unitconverter.View


import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.ScrollingMovementMethod
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
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
    private var myClipboard: ClipboardManager? = null
    private var myClip: ClipData? = null

    //lateinit var utility: Utility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

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
        textView.movementMethod = ScrollingMovementMethod()
        editText.maxLines = 1
        editText.isVerticalScrollBarEnabled = true
        editText.movementMethod = ScrollingMovementMethod()

        editText.customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {}
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }
        }

        if (Build.VERSION.SDK_INT >= 21) {
            editText!!.showSoftInputOnFocus = false
        } else if (Build.VERSION.SDK_INT >= 11) {
            editText!!.setRawInputType(InputType.TYPE_CLASS_TEXT)
            editText!!.setTextIsSelectable(true)
        } else {
            editText!!.setRawInputType(InputType.TYPE_NULL)

            editText!!.isFocusable = true
        }

        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val str = s.toString()

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
                //if (str == ".")
                //   editText.setText( "0")


            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                var str = s.toString()
                //if (str == ".")
                 //   editText.setText( "0")
                val cursorPosition: Int = editText.selectionStart
                if(str.startsWith('.') && cursorPosition!= 0)
                {
                    editText.setText("0".plus(s))
                    editText.setSelection(editText.text.length)
                }

                if(str.startsWith('0') && str.length >= 2 && str[1] != '.')
                {
                   str = str.substring(1)
                    /*Toast.makeText(
                        this@ConverterActivity,str, Toast.LENGTH_SHORT
                    ).show()*/
                    Toast.makeText(
                        this@ConverterActivity, "leading zeros were removed", Toast.LENGTH_SHORT
                    ).show()
                    editText.setText(str)
                    editText.setSelection(editText.text.length)
                }

               /* if(str.startsWith("0") && str.contains('.'))
                {
                    str = str.substring(1)
                    Toast.makeText(
                        this@ConverterActivity, "here", Toast.LENGTH_SHORT
                    ).show()
                   // editText.setSelection(str.length)


                }
                    //if(!str.contains('.')) {
                    //    str.trimStart('0')
                    //editText.setText(str)}


               // }*/
                val convertedNum : BigDecimal =
                    if (str.isEmpty() || str.toBigDecimalOrNull() == null)
                        BigDecimal("0")
                    else
                        BigDecimal(str)
                //editText.setText(convertedNum.toString())
                mainViewModel.setValueFrom(convertedNum)
                mainViewModel.convert()

               /* Toast.makeText(
                    this@ConverterActivity,"valueFrom" + mainViewModel.unitFrom.value?.let {
                        getString(
                            it.Name)
                    } + "valueto" + mainViewModel.unitTo.value?.Name?.let { getString(it) }, Toast.LENGTH_SHORT
                ).show()*/


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


        myClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?;

        fun copyText() {
            val sdk = Build.VERSION.SDK_INT
            if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as android.text.ClipboardManager
                clipboard.text = textView.text.toString()
            } else {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("text label", textView.text)
                clipboard.setPrimaryClip(clip)
            }
            Toast.makeText(this, "Number Copied", Toast.LENGTH_SHORT).show();
        }

        // on click paste button
        fun pasteText() {
            val abc = myClipboard?.getPrimaryClip()
            val item = abc?.getItemAt(0)
            val itemStr = item?.text.toString()
            if(itemStr.toBigDecimalOrNull() != null) {
                editText.setText(itemStr)
                editText.setSelection(editText.length())
                Toast.makeText(
                    applicationContext, "Number pasted",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                Toast.makeText(
                    applicationContext,
                    "Not allowed format to paste",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        val pasteButton = findViewById<Button>(R.id.pasteButton)
        pasteButton.setOnClickListener {
           pasteText()
        }

        val copyButton = findViewById<Button>(R.id.copyButton)
        copyButton.setOnClickListener {
            copyText()
        }

        val swapButton = findViewById<Button>(R.id.swapButton)
        swapButton.setOnClickListener {
            mainViewModel.swap()
            val spinner1Index: Int = spinner.getSelectedItemPosition()
            spinner.setSelection(spinner2.selectedItemPosition)
            spinner2.setSelection(spinner1Index)

        }
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


