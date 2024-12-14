package com.knyazev.bookprogrammingforprofessionals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.knyazev.bookprogrammingforprofessionals.CheatActivity.Companion.newIntent
import com.knyazev.bookprogrammingforprofessionals.Const.KEY_INDEX


class MainActivity : ComponentActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var queTextView: TextView
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val index = savedInstanceState?.getInt(KEY_INDEX) ?: 2
//        quizViewModel.currentIndex = index
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_id)
        falseButton = findViewById(R.id.false_id)
        backButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)
        queTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)

        cheatButton.setOnClickListener {
//            val intent = Intent(this, CheatActivity::class.java)
//            intent.putExtra(EXTRA_ANSWER_IS_TRUE, quizViewModel.currentQuestionAnswer)
            val intent1: Intent = newIntent(this@MainActivity, quizViewModel.currentQuestionAnswer)
            Log.i("!!!", "MainActivity ${intent1.hashCode()}")
            startActivity(intent1)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

//        backButton.setOnClickListener {
//            val currentIndex = quizViewModel.currentIndex
//            if (quizViewModel.currentIndex == 0) {
//                currentIndex = questionBank.size
//            }
//            currentIndex -= 1 % questionBank.size
//            updateQuestion()
//        }

        nextButton.setOnClickListener {
            //quizViewModel.moveToNext()
            updateQuestion()
        }

        queTextView.setOnClickListener() {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        val questionTextResId: Int = quizViewModel.currentQuestionText
        queTextView.setText(questionTextResId)
        Log.d("!!!", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d("!!!", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("!!!", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("!!!", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("!!!", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("!!!", "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("!!!", "onSaveInstanceState")
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        Log.i(TAG, "onSavedInstanceState")
//        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
//    }

    private fun updateQuestion() {
        val questionTextResId: Int = quizViewModel.currentQuestionText
        queTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId =
            if (userAnswer == correctAnswer) R.string.correct_toast
            else R.string.incorrect_toast
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
    }
}
