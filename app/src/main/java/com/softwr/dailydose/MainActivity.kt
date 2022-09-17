package com.softwr.dailydose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softwr.dailydose.mutex.MutexClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(IO).launch {
            val mutexClass = MutexClass()
            mutexClass.main()
        }

    }
}