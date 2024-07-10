package example.com.routing

import example.com.model.User
import example.com.routing.request.UserRequest
import example.com.routing.response.UserResponse
import example.com.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

/*
Here, we will need to specify the handlers like POST, GET, etc.
for every endpoint we would like to 'expose'
 */
fun Route.userRoute(
    userService: UserService
) {

    /*
    Read the api client 'payload'
    payload meaning: the part of transmitted data that is the actual intended message

    So, here, the json payload sent by the api client will be converted
    to the user request instance.

     */
    post {
        /*
        In Ktor, when we want to read the json tool,
        we involve the receive method. about 30 mins into video
         */
        val userRequest = call.receive<UserRequest>()

        //so, it will either be a) created or b) api client will receive bad request.
        val createdUser = userService.save(
            //specify the user request converted to user model
            user = userRequest.toModel()
            ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        /*
        Return 201 created and pass the identifier as the id header.
         */
            call.response.header(
                name = "id",
                value = createdUser.id.toString()
            )
            call.respond(
                HttpStatusCode.Created
            )
    }

    /*
    Now, we can specify the endpoint that will be responsible
    for returning all users.

    So, fetch the list of users, and then map each entry in the list
    to user response.

     */
    get {
            val users = userService.findAll()

            call.respond(
                message = users.map(User::toResponse)
            )
    }

    /*
    Now, we introduce the end point that is responsible for getting users by id.

     So, the identifier will be passed as part of the URL.
     */
    get("/{id}"){
        val id: String = call.parameters["id"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)

        val foundUser = userService.findById(id)
            ?: return@get call.respond(HttpStatusCode.NotFound)
        call.respond(
            message = foundUser.toResponse()
        )
    }
}

/*
Instantiate a new user from our user request
 */
private fun UserRequest.toModel(): User =
    User(
        id = UUID.randomUUID(),
        username = this.username,
        password = this.password
    )

private fun User.toResponse(): UserResponse =
    UserResponse(
        id = this.id,
        username = this.username
    )