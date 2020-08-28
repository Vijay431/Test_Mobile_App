package com.example.testapp

import android.content.Context
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal lateinit var tapMeButton : Button
    internal lateinit var gameScore : TextView
    internal lateinit var timer : TextView
    internal lateinit var countDownTimer: CountDownTimer

    internal var score : Int = 0
    internal var gameStarted : Boolean = false
    internal var initialCountdown : Long = 60000
    internal var countdownInterval : Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        gameScore = findViewById(R.id.scorecard)
        timer = findViewById(R.id.gameTimerTextView)

//        Initial Score as 0
        gameScore.text = getString(R.string.gameScore, score)

        tapMeButton.setOnClickListener {
            view -> incrementScore()
        }

        resetGame()
    }

    private fun resetGame() {
        score = 0;

        gameScore.text = getString(R.string.gameScore, score)

        var timeLeft = initialCountdown / 1000
        timer.text = getString(R.string.timeLeft, timeLeft)

        countDownTimer = object : CountDownTimer(initialCountdown, countdownInterval) {
            override fun onTick(millisuntilFinished : Long) {
                val timeleft = millisuntilFinished / 1000
                timer.text = getString(R.string.timeLeft, timeleft)
            }

            override fun onFinish() {
                endGame()

                score = 0
                gameScore.text = getString(R.string.gameScore, score)

                timeLeft = initialCountdown / 1000
                timer.text = getString(R.string.timeLeft, timeLeft)
            }
        }

        gameStarted = false
    }

    private fun incrementScore() {
        if(!gameStarted){
            startGame()
        }

        score += 10
        gameScore.text = getString(R.string.gameScore, score)
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.gameOvermessage, score), Toast.LENGTH_LONG ).show()
        gameStarted = false
    }


}