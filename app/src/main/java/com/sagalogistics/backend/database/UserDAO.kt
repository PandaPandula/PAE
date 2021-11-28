package com.sagalogistics.backend.database

import com.sagalogistics.backend.models.User
import java.util.concurrent.Future

interface UserDAO {
    fun add(user: User)
    fun get(key: String): Future<User?>
    fun update(key: String, user: User)
    fun delete(key: String)
    fun deleteBar(barKey: String)
}