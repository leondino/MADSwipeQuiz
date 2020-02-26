package com.example.swipequiz

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var questions = arrayListOf<Question>()
    private var questionAdapter = QuestionAdapter(questions, this)
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
        createItemTouchHelper().attachToRecyclerView(rvQuiz)

        for (iQuestion in questionArray.indices){
            questions.add(Question(questionArray[iQuestion],
                resources.getIntArray(R.array.answer_index_array)[iQuestion]))

            questions[iQuestion].answer = questions[iQuestion].answerIndex == 1
        }

        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper() : ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val givenAnswer : Boolean = direction != ItemTouchHelper.LEFT

                if(questions[position].answer == givenAnswer)
                    questions.removeAt(position)
                else
                    Snackbar.make(rvQuiz, resources.getString(R.string.wrong_answer), Snackbar.LENGTH_SHORT).show()

                questionAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }
}
