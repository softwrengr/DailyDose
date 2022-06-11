package com.softwr.dailydose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softwr.dailydose.Constants.Arrays
import com.softwr.dailydose.dataStructure.SearchingAlgorithms

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchingAlgorithms = SearchingAlgorithms()
        searchingAlgorithms.linearSearch(Arrays.sortedArray)
    }
}