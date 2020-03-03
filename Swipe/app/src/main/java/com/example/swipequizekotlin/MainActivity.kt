package com.example.swipequizekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val questions = arrayListOf<Question>()
    val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in Question.QUESTIONS_FOR_QUIZE.indices) {
            questions.add(Question(Question.QUESTIONS_FOR_QUIZE[i], Question.QUESTION_AWNSERS_FOR_QUIZE[i]))

        }
        questionAdapter.notifyDataSetChanged()
        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = StaggeredGridLayoutManager(1,1)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }

}
