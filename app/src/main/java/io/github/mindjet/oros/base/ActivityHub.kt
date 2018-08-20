package io.github.mindjet.oros.base

import java.util.*

object ActivityHub {

    private val activityStack: Stack<BaseActivity> = Stack()

    fun register(activity: BaseActivity) {
        activityStack.push(activity)
    }

    fun unregister(activity: BaseActivity) {
        activityStack.pop()
    }

    fun getCurrentActivity(): BaseActivity = activityStack.peek()

}