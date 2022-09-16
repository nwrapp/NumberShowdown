package com.example.numberShowdown

import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.util.Log
import kotlin.random.Random

class Polynomial(val degree: Int, val max: Int, val variableValue: Int, val variableName: Char) {
    val coefficients = generateCoefficients()
    val totalValue = solvePolynomial(variableValue)
    val stringValue = generateString(variableName)

    fun generateCoefficients(): IntArray {
        return IntArray(degree) { Random.nextInt(-max, max) }
    }

    fun solvePolynomial(varValue: Int): Int {
        var result = 0
        for (i in coefficients.indices) {
            result += Math.pow(varValue.toDouble(), i.toDouble()).toInt() * coefficients[i]
        }
        return result
    }

    fun generateString(varName: Char): String {
        var str = ""
        for (i in coefficients.size-1 downTo 0) {
            str += generateTerm(varName, i)
        }
        return str
    }

    private fun generateTerm(varName: Char, index: Int): String {
        var term = ""
        if (degree == 1) {
            term += coefficients[index]
        } else if (degree > 1) {
            var coefficient = coefficients[index]
            var operator = if (coefficient >= 0) "+" else ""
            var exponent = index

            if (exponent == 0) {
                term += operator + coefficient.toString()
            } else if (exponent == 1) {
                term += operator + coefficient.toString() + varName
            } else {
                term += operator + coefficient.toString() + varName + exponent
            }
        }
        return term
    }

    fun printReport() {
        var coeff = degree.toString() + "{ "
        for (i in coefficients.indices) {
            coeff += coefficients[i].toString() + " "
        }
        coeff += "}"

        Log.i("Coefficient Sequence", coeff)
        Log.i("Polynomial evaluates to", totalValue.toString())
        Log.i("Polynomial represented as ", stringValue)
    }
}