package com.example.swipequizekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questions.add(Question("Web development is better than mobile development", true))
        questions.add(Question("Mobile Application Development grants 12 ETCS", true))
        questions.add(Question("A unit in kotlin corresponds to a void in Java", false))
        questions.add(Question("In kotlin 'when' replaces the 'switch' operator in Java", true))

        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = StaggeredGridLayoutManager(1, 1)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }


    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val swipeDirection = direction == ItemTouchHelper.RIGHT
                val answerSwipe = if (swipeDirection == questions[position].questionAwnser) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                    viewHolder.itemView.context.getString(R.string.correctAwnser)
                } else {
                    questionAdapter.notifyItemChanged(position)
                    viewHolder.itemView.context.getString(R.string.incorrectAwnser)
                }

                Snackbar.make(viewHolder.itemView, answerSwipe, Snackbar.LENGTH_SHORT).show()
            }
        }
        return ItemTouchHelper(callback)
    }
}
