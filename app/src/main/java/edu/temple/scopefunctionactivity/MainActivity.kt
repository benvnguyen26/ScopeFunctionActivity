package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Logging function outputs to Logcat
        Log.d("Function Output", "Test Data Array: ${getTestDataArray()}")

        val testList = listOf(1.0, 3.0, 5.0, 7.0, 9.0)
        Log.d("Function Output", "Avg < Median: ${averageLessThanMedian(testList)}")

        val context = this
        val view = getView(0, null, listOf(10, 20, 30), context)
        Log.d("Function Output", "View text: ${(view as TextView).text}")
    }

    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    //apply sorts the list of 10 integers created by mutablelist
    private fun getTestDataArray() = MutableList(10) { Random.nextInt() }.apply { sort() }

    // Return true if average value in list is greater than median value, false otherwise
    //sorted list is passed to let directly  ~ removes declarations
    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.sorted().let { sortedList ->
            val median = if (sortedList.size % 2 == 0)
                (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
            else
                sortedList[sortedList.size / 2]
            listOfNumbers.average() < median
        }


    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    //let for median calculation ? ?: for null checks
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }
}
