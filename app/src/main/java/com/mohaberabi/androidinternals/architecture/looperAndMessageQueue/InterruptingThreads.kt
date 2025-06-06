package com.mohaberabi.androidinternals.architecture.looperAndMessageQueue

import kotlin.concurrent.thread


class InterruptingThreads() {

    private var currentThread: Thread? = null
    fun testThreadInterrupted() {
        currentThread = currentThread ?: thread {
            try {
                println("currentThread started")
                Thread.sleep(2000L)
                inturrptThread()
                println("currentThread working again even after Interrupted")
            } catch (e: InterruptedException) {
            }

        }
    }

    private fun inturrptThread() {
        throw InterruptedException("currentThread Interrupted")
    }
}