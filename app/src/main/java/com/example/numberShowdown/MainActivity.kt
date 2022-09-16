package com.example.numberShowdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private var playerScore = 0
    private var currentDegree = 1;
    private var currentMax = 10
    private var currentVarName = 'x'
    private var currentVarVal = 1
    private var leftPolynomial = Polynomial(currentDegree, currentMax, currentVarVal, currentVarName)
    private var rightPolynomial = Polynomial(currentDegree, currentMax, currentVarVal, currentVarName)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val leftButton: Button = findViewById(R.id.left_button)
        leftButton.setOnClickListener {
            buttonPressed(0)
        }

        val rightButton: Button = findViewById(R.id.right_button)
        rightButton.setOnClickListener {
            buttonPressed(1)
        }

        refreshNumberButtons()
    }

    private fun buttonPressed(signal: Int) {
        val result: Boolean = if (signal == 0) {
            // Left Button Press
            compareButtonValues(leftPolynomial.totalValue, rightPolynomial.totalValue)
        } else {
            // Right Button Press
            compareButtonValues(rightPolynomial.totalValue, leftPolynomial.totalValue)
        }
        if (result) {
            Toast.makeText(this,"Correct! +1 Point", Toast.LENGTH_SHORT).show()
            updateScore(1)
        } else {
            Toast.makeText(this,"Incorrect! -1 Point", Toast.LENGTH_SHORT).show()
            updateScore(-1)
        }
        refreshNumberButtons()
    }

    private fun compareButtonValues(s: Int, t: Int): Boolean {
        return s > t
    }

    private fun updateScore(i: Int) {
        playerScore += i
        val text = getString(R.string.score_label, playerScore)
        val score: TextView = findViewById(R.id.score_textView)
        score.text = text
    }

    private fun refreshNumberButtons() {
        refreshCurrentSettings()
        updatePolynomialValues()
        Log.i("Refreshed values", leftPolynomial.totalValue.toString()+", "+rightPolynomial.totalValue.toString())
        updateButtonText()
        updateVariableText()
    }

    private fun refreshCurrentSettings() {
        if (playerScore > 0) {
            currentDegree = (playerScore / 10) + 1
            currentVarVal = nextInt(0, playerScore)
        } else {
            currentDegree = 1
            currentVarVal = 0
        }
    }

    private fun updatePolynomialValues() {
        leftPolynomial = Polynomial(currentDegree, currentMax, currentVarVal, currentVarName)
        rightPolynomial = Polynomial(currentDegree, currentMax, currentVarVal, currentVarName)

        while (rightPolynomial.totalValue == leftPolynomial.totalValue) {
            rightPolynomial= Polynomial(currentDegree, currentMax, currentVarVal, currentVarName)
        }
    }

    private fun updateButtonText() {
        val l: Button = findViewById(R.id.left_button)
        val r: Button = findViewById(R.id.right_button)
        l.text = leftPolynomial.stringValue
        r.text = rightPolynomial.stringValue
    }

    private fun updateVariableText() {
        val current: TextView = findViewById(R.id.variable_textView)
        val text: String
        if (currentDegree == 1) {
            text = getString(R.string.constant_label)
        } else {
            text = getString(R.string.variable_label, currentVarName, currentVarVal)
        }
        current.text = text
    }
}