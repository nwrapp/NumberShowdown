// author @Nolan Rapp
package com.example.numberShowdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {
    private var playerScore = 0
    private var leftValue = 0
    private var rightValue = 0
    private var leftDisplay = ""
    private var rightDisplay = ""

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
            compareButtonValues(leftValue, rightValue)
        } else {
            // Right Button Press
            compareButtonValues(rightValue, leftValue)
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

    private fun refreshNumberButtons() {
        refreshButtonValues()
        refreshDisplayVariables()
        updateButtonText()
    }

    private fun refreshButtonValues() {
        leftValue = nextInt(100)
        rightValue = nextInt(100)

        while (rightValue == leftValue) {
            rightValue = nextInt(100)
        }
    }

    private fun refreshDisplayVariables() {
        leftDisplay = leftValue.toString()
        rightDisplay = rightValue.toString()
    }

    private fun updateButtonText() {
        val l: Button = findViewById(R.id.left_button)
        val r: Button = findViewById(R.id.right_button)
        l.text = leftDisplay
        r.text = rightDisplay
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
}