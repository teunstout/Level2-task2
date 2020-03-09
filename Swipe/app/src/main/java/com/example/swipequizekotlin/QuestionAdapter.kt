package com.example.swipequizekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_layout.view.*

class QuestionAdapter(val questions: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(textview: View) : RecyclerView.ViewHolder(textview) {

        fun bind(question: Question) {
            itemView.question.text = question.quizQuestion
            // https://stackoverflow.com/questions/52765121/how-do-i-call-getstring-inside-the-onbindviewholder-method-of-a-recycler-vie
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    itemView.context.getString(R.string.awnser, question.questionAwnser),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

}
