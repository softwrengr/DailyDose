package com.softwr.dailydose.mutex

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MutexClass {

    private val mutex = Mutex()  // our hero
    private var counterWithMutex = 0
    private var counterNoMutex = 0


    suspend fun main( ) {

        val job1NoMutex = CoroutineScope(Default).launch {
            for (i in 1..500) {
                incrementCounterByTenNoMutex()
            }
        }

        val job2NoMutex = CoroutineScope(Default).launch {
            for (i in 1..500) {
                incrementCounterByTenNoMutex()
            }
        }


        val job1WithMutex = CoroutineScope(Default).launch {
            for (i in 1..500) {
                incrementCounterByTenWithMutex()
            }
        }

        val job2WithMutex = CoroutineScope(Default).launch {
            for (i in 1..500) {
                incrementCounterByTenWithMutex()
            }
        }


        joinAll(job1NoMutex, job2NoMutex, job1WithMutex, job2WithMutex)

        println("NoMutexTally: $counterNoMutex")
        println("WithMutexTally: $counterWithMutex")
    }

    private suspend fun incrementCounterByTenWithMutex() {
        mutex.withLock {
            for (i in 0 until 10) {
                counterWithMutex++
            }
        }
    }

    private fun incrementCounterByTenNoMutex() {
        for (i in 0 until 10) {
            counterNoMutex++
        }
    }
}