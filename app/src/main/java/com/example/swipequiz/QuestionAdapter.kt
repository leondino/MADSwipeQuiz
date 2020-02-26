package com.example.swipequiz

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_question.view.*
import kotlin.coroutines.coroutineContext

class QuestionAdapter(private val questions: List<Question>, private val mainContext: Context) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(question: Question){
            itemView.questionText.text = question.theQuestion
            itemView.setOnClickListener {
                Toast.makeText(mainContext, question.answer.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}