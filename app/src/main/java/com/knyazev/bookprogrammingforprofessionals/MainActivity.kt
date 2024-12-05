package com.knyazev.bookprogrammingforprofessionals

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var queTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
    )

    private var currentIndex = questionBank.size - 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_id)
        falseButton = findViewById(R.id.false_id)
        backButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
        queTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        backButton.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questionBank.size
            }
            currentIndex -= 1 % questionBank.size
            updateQuestion()
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        queTextView.setOnClickListener() {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        val questionTextResId: Int = questionBank[currentIndex].textResId
        queTextView.setText(questionTextResId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun updateQuestion() {
        val questionTextResId: Int = questionBank[currentIndex].textResId
        queTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId =
            if (userAnswer == correctAnswer) R.string.correct_toast
            else R.string.incorrect_toast
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}
