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
    val questions = arrayListOf<Question>() // arraylist of dataclass question
    val questionAdapter =
        QuestionAdapter(questions)    // question adapter geef je alle data objects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // voor elk companion object een nieuwe dataclass aanmaken
        for (i in Question.QUESTIONS_FOR_QUIZE.indices) {
            questions.add(
                Question(
                    Question.QUESTIONS_FOR_QUIZE[i],
                    Question.QUESTION_AWNSERS_FOR_QUIZE[i]
                )
            )
        }

        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = StaggeredGridLayoutManager(1, 1) // gebruik een staggerd grid
        rvQuestions.adapter = questionAdapter // adapter recycle view is questionadapter
        // scheid de lijntjes doormiddel van een verticale lijn
        rvQuestions.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }


    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
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
                var awnserSwipe = ""
                
                if (swipeDirection == questions[position].questionAwnser) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                    awnserSwipe = viewHolder.itemView.context.getString(R.string.correctAwnser)
                } else {
                    questionAdapter.notifyItemChanged(position)
                    awnserSwipe = viewHolder.itemView.context.getString(R.string.incorrectAwnser)
                }
                Snackbar.make(
                    viewHolder.itemView,
                    awnserSwipe,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return ItemTouchHelper(callback)
    }

}
