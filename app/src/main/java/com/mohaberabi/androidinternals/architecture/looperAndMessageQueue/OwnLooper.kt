package com.mohaberabi.androidinternals.architecture.looperAndMessageQueue

import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread


class OwnLooper() {
    private var currentThread: Thread? = null

    private val messageQueue = LinkedBlockingQueue<Runnable>()

    fun enqueue(runnable: Runnable) {
        if (currentThread == null) {
            createOwnThread()
        }
        messageQueue.put(runnable)
    }

    private fun createOwnThread() {
        currentThread = currentThread ?: thread {
            try {
                while (true) {
                    if (messageQueue.isNotEmpty()) {
                        messageQueue.take().run()
                    }
                }
            } catch (e: InterruptedException) {
                return@thread
            }
        }
    }

    fun forceQuit() {
        currentThread?.interrupt()
        currentThread = null
    }

    fun quitSafely() {

    }
}