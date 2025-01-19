package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        // Find the TextView by its ID
        val textView = view.findViewById<TextView>(R.id.fragmentBText)

        // Set custom text to the TextView
        textView.text = "Exploring Fragment B"

        return view
    }
}
