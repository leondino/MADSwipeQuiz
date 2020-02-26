package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var questions = arrayListOf<Question>()
    private var questionAdapter = QuestionAdapter(questions)
    private var questionArray = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        initView()
    }

    private fun initView(){
        rvQuiz.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvQuiz.adapter = questionAdapter
        rvQuiz.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        questionArray = resources.getStringArray(R.array.questions_array)

        for (iQuestion in questionArray.indices){
            questions.add(Question(questionArray[iQuestion],
                resources.getIntArray(R.array.answer_index_array)[iQuestion]))

            questions[iQuestion].answer = questions[iQuestion].answerIndex == 1
        }

        questionAdapter.notifyDataSetChanged()
    }

    private fun creatItemTouchHelper() : ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback()
        return ItemTouchHelper(callback)
    }
}
