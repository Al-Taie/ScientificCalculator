package com.example.scientificcalculator

import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.Html.fromHtml
import android.text.Spanned
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.scientificcalculator.backend.Backend
import com.example.scientificcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        switchMode()
    }

    private fun switchMode() {
        Backend.switchMode(binding = binding)
    }

    fun onNumClick(view: View) {
        Backend.onNumClick(view = view, binding = binding)
    }

    fun onOperationClick(view: View) {
        Backend.onOperationClick(view = view, binding = binding)
    }

    fun onTriangleClick(view: View) {
        Backend.onTriangleClick(view = view, binding = binding, context = this)
    }

    fun onSignalClick(view: View) {
        Backend.onSignalClick(binding = binding)
    }

    fun onDeleteClick(view: View) {
        Backend.onDeleteClick(binding = binding)
    }

    fun onClearClick(view: View) {
        Backend.onClearClick(binding = binding)
    }
}
