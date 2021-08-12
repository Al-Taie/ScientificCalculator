package com.example.scientificcalculator.util

import android.content.Context
import android.widget.Toast
import com.example.scientificcalculator.data.DataManager
import com.example.scientificcalculator.databinding.ActivityMainBinding

fun showToast(context: Context, msg: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, msg, duration).show()
}

fun doOperation(value: Double, binding: ActivityMainBinding) {
    DataManager.RESULT = when (DataManager.CURRENT_OP) {
        Operator.ADD -> DataManager.RESULT + value
        Operator.SUB -> DataManager.RESULT - value
        Operator.MUL -> DataManager.RESULT * value
        Operator.DIV -> DataManager.RESULT / value
    }

    binding.resultView.text = DataManager.RESULT.toString()

    DataManager.isOp = false
}

