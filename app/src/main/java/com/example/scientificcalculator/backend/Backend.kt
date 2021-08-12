package com.example.scientificcalculator.backend

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import androidx.core.text.isDigitsOnly
import androidx.core.view.children
import com.example.scientificcalculator.R
import com.example.scientificcalculator.data.DataManager
import com.example.scientificcalculator.databinding.ActivityMainBinding
import com.example.scientificcalculator.util.*
import kotlin.math.*

object Backend {
    @SuppressLint("SetTextI18n")
    fun onSignalClick(view: View? = null, binding: ActivityMainBinding) {
        DataManager.FIRST_VALUE = "-" + DataManager.FIRST_VALUE

        if (DataManager.isNegative) {
            binding.operationView.let { it.text = it.text.removePrefix("-") }
            DataManager.isNegative = false
        } else {
            binding.operationView.let { it.text = "-${it.text}" }
            DataManager.isNegative = true
        }
    }

    @SuppressLint("SetTextI18n")
    fun onNumClick(view: View, binding: ActivityMainBinding) {
        val value = (view as Button).text.toString()

        if (DataManager.isOp) {
            binding.operationView.let { it.text = "(${it.text}$value)" }
            doOperation(value = value.toDouble(), binding = binding)
        } else if (binding.operationView.text.toString().isDigitsOnly()) {
            binding.operationView.let { it.text = "${it.text}$value" }
            DataManager.FIRST_VALUE = binding.operationView.text.toString()
        } else {
            binding.operationView.let { it.text = "${it.text}$value" }
            DataManager.FIRST_VALUE = DataManager.RESULT_VALUE.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    fun onTriangleClick(view: View, binding: ActivityMainBinding, context: Context) {
        val value = (view as Button).text

        with(binding) {

            if (operationView.text.isEmpty()) {
                showToast(context, "Click a Number Fist!")
                return
            }

            operationView.let { it.text = "$value(${it.text})" }

            resultView.text.takeIf { it.isNotEmpty() }?.toString()?.toDouble()?.let {
                DataManager.RESULT_VALUE = it
            }
        }

        DataManager.RESULT = when (view.id) {
            R.id.btn_abs -> abs(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_sin -> sin(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_cos -> cos(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_tan -> tan(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_sec -> 1 / sin(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_csc -> 1 / cos(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_cot -> 1 / tan(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_sqrt -> sqrt(DataManager.FIRST_VALUE.toDouble())
            R.id.btn_log2 -> log(DataManager.FIRST_VALUE.toDouble(), 2.0)
            R.id.btn_log10 -> log(DataManager.FIRST_VALUE.toDouble(), 10.0)
            R.id.btn_ln -> ln(DataManager.FIRST_VALUE.toDouble())
            else -> 0.0
        }

        binding.resultView.text = DataManager.RESULT.toString()
    }

    @SuppressLint("SetTextI18n")
    fun onOperationClick(view: View, binding: ActivityMainBinding) {
        val buttonValue = (view as Button).text

        with(binding) {
            if (DataManager.isOp)
                operationView.let { it.text = "${it.text}$buttonValue" }

            resultView.text.takeIf { it.isNotEmpty() }?.toString()?.toDouble()?.let {
                DataManager.RESULT_VALUE = it
            }
        }

        if (DataManager.RESULT == 0.0)
             DataManager.RESULT = DataManager.FIRST_VALUE.toDouble()

        DataManager.CURRENT_OP = when (view.id) {
            R.id.btn_plus -> Operator.ADD
            R.id.btn_minus -> Operator.SUB
            R.id.btn_mul -> Operator.MUL
            R.id.btn_div -> Operator.DIV
            else -> Operator.ADD
        }
        DataManager.isOp = true
    }

    fun onDeleteClick(view: View? = null, binding: ActivityMainBinding) {
        binding.operationView.takeIf { it.text.isNotEmpty() }?.let {
            it.text = it.text.removeSuffix(it.text.last().toString())
        }
    }

    fun onClearClick(view: View? = null, binding: ActivityMainBinding) {
        binding.operationView.text = ""
        binding.resultView.text = ""
        DataManager.FIRST_VALUE = ""
        DataManager.RESULT_VALUE = 0.0
        DataManager.RESULT = 0.0
        DataManager.isNegative = false
        DataManager.isOp = false
    }

    fun switchMode(binding: ActivityMainBinding) {
        with(binding) {
            radioNormal.setOnClickListener {
                radioNormal.setTypeface(null, Typeface.BOLD)
                radioScientific.setTypeface(null, Typeface.NORMAL)
                radioScientific.alpha = 0.75f
                radioNormal.alpha = 1f
                scientificLayout.children.forEach { it.isEnabled = false; it.alpha = 0.75f }
            }

            radioScientific.setOnClickListener {
                radioScientific.setTypeface(null, Typeface.BOLD)
                radioNormal.setTypeface(null, Typeface.NORMAL)
                radioNormal.alpha = 0.75f
                radioScientific.alpha = 1f
                scientificLayout.children.forEach { it.isEnabled = true; it.alpha = 1f }
            }
        }
    }
}