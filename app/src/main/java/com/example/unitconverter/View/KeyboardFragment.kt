package com.example.unitconverter.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.unitconverter.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeyboardFragment : Fragment() {

    //private lateinit var viewOfLayout: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myKeyboard = view.findViewById<MyKeyboard>(R.id.keyboard)
        val editText: EditText = requireActivity().findViewById(R.id.editTextInputDecimal)
        val inputConnection = editText.onCreateInputConnection(EditorInfo())
        myKeyboard.setInputConnection(inputConnection)
    }
    /*companion object
    {
        @JvmStatic
        fun newInstance(param1: String, param2: String){

        }
    }*/

}