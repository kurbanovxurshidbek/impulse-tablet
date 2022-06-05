package com.impulse.impulse_driver.database

open class Event<out T>(private val content: T) {
    var hasBeenHandler = false
    private set  //Allow external read but  not write


    /**
     * Returns the content and prevents its use again
     * **/

    fun getContentIfNotHandler(): T? {
        return if (hasBeenHandler) {
            null
        }else {
            hasBeenHandler = true
            content
        }
    }

    /**
     * Returns the content even if it's already been handler
     * **/
}