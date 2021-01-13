package com.bignerdranch.geoquizV2
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private val TAG = "MainActivity"
    private val KEY_INDEX = "index"

    //THIS WAS IN THE BOOK AND IS DEPRECATED THE FIX IS IN THE CODE BELOW
    //val quizViewModel: QuizViewModel by lazy{
    //        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    //    }

    //THIS IS THE FIX

    val quizViewModel: QuizViewModel by lazy{
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            //THIS CHECKS THE CURRENT INDEX FOR NULL OF FOUND ENTER 0 IF NOT ENTER THE CURRENT INDEX
            //THIS IS A BACK UP TO VIEWMODEL IF THE APP DIES COMPLETLY AND VIEW MODEL IS DESTROYED
            val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
            quizViewModel.currentIndex = currentIndex

            Log.d(TAG,"Got a quiz view model: $quizViewModel")

            trueButton = findViewById(R.id.true_button)
            falseButton = findViewById(R.id.false_button)
            nextButton = findViewById(R.id.next_button)
            questionTextView = findViewById(R.id.question_text_view)

            //PAGE 87 OF THE BOOK JUST NOT INCLUDE THIS CHANGE
            val questionTextResId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextResId)


            trueButton.setOnClickListener { view: View ->
                checkAnswer(true)
            }

            falseButton.setOnClickListener{view: View ->
                checkAnswer(false)
            }

            nextButton.setOnClickListener {
                //currentIndex = (currentIndex + 1) % questionBank.size
                quizViewModel.moveToNext()
                updateQuestion()
            }

        }
        private fun updateQuestion() {
            //val questionTextResId = questionBank[currentIndex].textResId
            val questionTextResId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextResId)
        }

        private fun checkAnswer(userAnswer: Boolean) {
            //val correctAnswer = questionBank[currentIndex].answer
            val correctAnswer = quizViewModel.currentQuestionAnswer

            val messageResId = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.incorrect_toast
            }

            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
        }

        //HERE I WILL BE ADDING SOME METHODS TO DETECT LIFECYCLE FUNCTIONS

        override fun onStart(){
            super.onStart();
            Log.d(TAG,"onStart() called");
        }
        override fun onResume(){
            super.onResume();
            Log.d(TAG,"onResume() called");
        }
        override fun onPause(){
            super.onPause();
            Log.d(TAG,"onPause() called");
        }

        override fun onSaveInstanceState(savedInstanceState: Bundle) {
            super.onSaveInstanceState(savedInstanceState)
            Log.i(TAG,"onsaveinstancestate")
            savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        }

        override fun onStop(){
            super.onStop();
            Log.d(TAG,"onStop() called")
        }
        override fun onDestroy(){
            super.onDestroy();
            Log.d(TAG,"onDestroy() called")
        }
    }


