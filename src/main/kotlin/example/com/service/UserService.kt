package example.com.service

import example.com.model.User
import example.com.repository.UserRepository
import java.util.*

/*
This will make use of the implemented user repository
and expose 4 methods.
 */
class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<User> =
        userRepository.findAll()

    fun findById(id: String): User? =
        userRepository.findById(
            //convert into UUID type from String
            id = UUID.fromString(id)
        )
        fun findByUsername(username: String): User?=
            userRepository.findByUserName(username)

    /*
    Now, add a save method that tries to save a user.
    If successful, will return a user. Otherwise, will
    return null.
     */
    fun save(user: User): User? {
        val foundUser = findByUsername(user.username)

        return if(foundUser == null){
            userRepository.save(user)
            user
        } else null

    }
}