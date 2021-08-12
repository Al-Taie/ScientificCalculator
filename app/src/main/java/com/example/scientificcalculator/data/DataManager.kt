package com.example.scientificcalculator.data

import com.example.scientificcalculator.util.Operator


object DataManager {
    var  CURRENT_OP = Operator.ADD
    var FIRST_VALUE: String = ""
    var RESULT_VALUE: Double = 0.0
    var RESULT: Double = 0.0
    var isOp = false
    var isNegative = false
}