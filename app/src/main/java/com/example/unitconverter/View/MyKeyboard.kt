package com.example.unitconverter.View


import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import com.example.unitconverter.R


class MyKeyboard(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr), View.OnClickListener, View.OnLongClickListener {
    // constructors
    constructor(context: Context?) : this(context, null, 0) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    // keyboard keys (buttons)
    private var mButton1: Button? = null
    private var mButton2: Button? = null
    private var mButton3: Button? = null
    private var mButton4: Button? = null
    private var mButton5: Button? = null
    private var mButton6: Button? = null
    private var mButton7: Button? = null
    private var mButton8: Button? = null
    private var mButton9: Button? = null
    private var mButton0: Button? = null
    private var mButtonDelete: Button? = null
    private var mButtonDot: Button? = null

    // This will map the button resource id to the String value that we want to
    // input when that button is clicked.
    var keyValues = SparseArray<String>()
    var iConnection: InputConnection? = null

    // Our communication link to the EditText
    private fun init(context: Context?, attrs: AttributeSet?) {

        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true)
        mButton1 = findViewById<View>(R.id.button_1) as Button
        mButton2 = findViewById<View>(R.id.button_2) as Button
        mButton3 = findViewById<View>(R.id.button_3) as Button
        mButton4 = findViewById<View>(R.id.button_4) as Button
        mButton5 = findViewById<View>(R.id.button_5) as Button
        mButton6 = findViewById<View>(R.id.button_6) as Button
        mButton7 = findViewById<View>(R.id.button_7) as Button
        mButton8 = findViewById<View>(R.id.button_8) as Button
        mButton9 = findViewById<View>(R.id.button_9) as Button
        mButton0 = findViewById<View>(R.id.button_0) as Button
        mButtonDelete = findViewById<View>(R.id.button_delete) as Button
        mButtonDot = findViewById<View>(R.id.button_dot) as Button

        // set button click listeners
        mButton1!!.setOnClickListener(this)
        mButton2!!.setOnClickListener(this)
        mButton3!!.setOnClickListener(this)
        mButton4!!.setOnClickListener(this)
        mButton5!!.setOnClickListener(this)
        mButton6!!.setOnClickListener(this)
        mButton7!!.setOnClickListener(this)
        mButton8!!.setOnClickListener(this)
        mButton9!!.setOnClickListener(this)
        mButton0!!.setOnClickListener(this)
        mButtonDelete!!.setOnClickListener(this)
        mButtonDot!!.setOnClickListener(this)

        mButtonDelete!!.setOnLongClickListener(this)
        mButtonDot?.isClickable = false

        // map buttons IDs to input strings
        keyValues.put(R.id.button_1, "1")
        keyValues.put(R.id.button_2, "2")
        keyValues.put(R.id.button_3, "3")
        keyValues.put(R.id.button_4, "4")
        keyValues.put(R.id.button_5, "5")
        keyValues.put(R.id.button_6, "6")
        keyValues.put(R.id.button_7, "7")
        keyValues.put(R.id.button_8, "8")
        keyValues.put(R.id.button_9, "9")
        keyValues.put(R.id.button_0, "0")
        keyValues.put(R.id.button_dot, ".")


    }

    override fun onLongClick(v: View?): Boolean {
        if (iConnection == null) return false
        val Text = iConnection!!.getExtractedText(ExtractedTextRequest(), 0).text

          iConnection!!.deleteSurroundingText(Text.length, 0)
        return true
    }
    override fun onClick(v: View) {

        // do nothing if the InputConnection has not been set yet
        if (iConnection == null) return
       // if(iConnection!!.getTextBeforeCursor(1,0 ) == "")
           // mButtonDot?.isClickable = false



        // Delete text or input key value
        // All communication goes through the InputConnection
        if (v.id == R.id.button_delete) {
            val selectedText = iConnection!!.getSelectedText(0)
            if (TextUtils.isEmpty(selectedText)) {
                // no selection, so delete previous character
                iConnection!!.deleteSurroundingText(1, 0)
                if(TextUtils.isEmpty(iConnection!!.getTextBeforeCursor(1,0)))
                    buttonAccessible(mButtonDot,false)
            } else {
                // delete the selection
                iConnection!!.commitText("", 1)
                if(TextUtils.isEmpty(iConnection!!.getTextBeforeCursor(1,0)))
                    buttonAccessible(mButtonDot,false)
            }
        }

        else {
            val value = keyValues[v.id]
            val currentText: CharSequence =
                iConnection!!.getExtractedText(ExtractedTextRequest(), 0).text

            if(currentText == "0" && value != "."){
                iConnection!!.deleteSurroundingText(1, 0)
                if(TextUtils.isEmpty(iConnection!!.getTextBeforeCursor(1,0)))
                    buttonAccessible(mButtonDot,false)
            }


            iConnection!!.commitText(value, 1)
            buttonAccessible(mButtonDot,true)

        }
    }

    // The activity (or some parent or controller) must give us
    // a reference to the current EditText's InputConnection
    fun setInputConnection(ic: InputConnection?) {
        iConnection = ic
    }



    init {
        init(context, attrs)
    }

    private fun buttonAccessible(button: Button?, b:Boolean)
    {
        button?.isClickable = b
    }
}