package com.impulse.impulse_driver.database

import android.app.Application
import com.impulse.impulse_driver.database.dao.PostDao
import com.impulse.impulse_driver.manager.RoomManager


class AppDatabase(application: Application) {
    var postDao: PostDao? = null

    init {
        val db = RoomManager.getInstance(application)
        this.postDao = db.postDao()
    }
}