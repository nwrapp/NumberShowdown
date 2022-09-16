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

        val scoreTextView: TextView = findViewById(R.id.score_textView)
        val leftButton: Button = findViewById(R.id.left_button)
        val rightButton: Button = findViewById(R.id.right_button)

        refreshNumberButtons(leftButton, rightButton)

        leftButton.setOnClickListener {
            buttonPressed(0, leftButton, rightButton, scoreTextView)
        }

        rightButton.setOnClickListener {
            buttonPressed(1, leftButton, rightButton, scoreTextView)
        }
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

    private fun updateButtonText(l: Button, r: Button) {
        l.text = leftDisplay
        r.text = rightDisplay
    }

    private fun compareButtonValues(s: Int, t: Int): Boolean {
        return s > t
    }

    private fun refreshNumberButtons(l: Button, r: Button) {
        refreshButtonValues()
        refreshDisplayVariables()
        updateButtonText(l, r)
    }

    private fun buttonPressed(signal: Int, l: Button, r: Button, score: TextView) {
        val result: Boolean = if (signal == 0) {
            // Left Button Press
            compareButtonValues(leftValue, rightValue)
        } else {
            // Right Button Press
            compareButtonValues(rightValue, leftValue)
        }
        if (result) {
            Toast.makeText(this,"Correct! +1 Point", Toast.LENGTH_SHORT).show()
            updateScore(1, score)
        } else {
            Toast.makeText(this,"Incorrect! -1 Point", Toast.LENGTH_SHORT).show()
            updateScore(-1, score)
        }
        refreshNumberButtons(l, r)
    }

    private fun updateScore(i: Int, score: TextView) {
        playerScore += i
        val text = getString(R.string.score_label, playerScore)
        score.text = text
    }
}