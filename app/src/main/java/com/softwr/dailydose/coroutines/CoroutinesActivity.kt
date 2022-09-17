package com.softwr.dailydose.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.softwr.dailydose.R
import com.softwr.dailydose.databinding.ActivityCoroutinesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Coroutines is concurrency design pattern to execute code asynchronously.
 * Coroutines help to manage long running tasks that can block main thread.
 * We can switch coroutines easily like start in one thread, pause in other thread and
 * get result in other thread.
 * Coroutines is light weight thread and we can run many coroutines in one thread due to suspension.
 */
class CoroutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            fakeApiCall1()
        }


    }


    /**
     * Coroutines scope keep track of any coroutines. it predict the lifecycle of coroutines.
     * Coroutines scope create coroutines and we can start more coroutines inside it like parent child.
     */
    private fun updateUI(value: String) {
        CoroutineScope(Main).launch {
            getThread()
            binding.tvResult.text = value
        }
    }

    private fun fakeApiCall1() {
        val startTime = System.currentTimeMillis();

        val parentJob = CoroutineScope(IO).launch {

            val job1 = launch {
                val result1 = getApiResult1()
                updateUI(result1)
            }

            val job2 = launch {
                val result2 = getApiResult2()
                updateUI(result2)
            }

        }

        parentJob.invokeOnCompletion {
            Log.d("Total Time elapsed", "${System.currentTimeMillis() - startTime}")
        }
    }

    private suspend fun getApiResult1(): String {
        getThread()
        delay(2000)

        return "Result 1"
    }

    private suspend fun getApiResult2(): String {
        getThread()
        delay(2000)

        return "Result 2"
    }

    private fun getThread() {
        Log.d("Thread : ", Thread.currentThread().name)
    }

}