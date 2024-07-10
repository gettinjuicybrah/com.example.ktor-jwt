package example.com.repository

import example.com.model.User
import java.util.*

/*
This will eventually be the code responsible for talking
to the database.

For now, it is a dummy repository that is just a mutable list
that stores resources in memory.

In this dummy repository, we will store passwords as a
plain text.

IN REAL LIFE SCENARIOS, WE SHOULD ALWAYS
ENCRYPT BEFORE PERSISTING.

BLOG POST ON THIS CODERSEE.COM
 */
class UserRepository {

    private val users = mutableListOf<User>()

    fun findAll(): List<User> = users

    fun findById(id: UUID): User? =
        users.firstOrNull {it.id == id}

    fun findByUserName(username: String): User? =
        users.firstOrNull{ it.username == username}

    fun save(user: User): Boolean =
        users.add(user)

}