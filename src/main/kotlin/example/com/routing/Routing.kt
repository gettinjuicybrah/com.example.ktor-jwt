package example.com.routing

import example.com.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService
) {
    routing {
        /*
        So, to sum up:
        Whenever a request is made to /api/user
        it should look for a handler inside the userRoute.
         */
        route("/api/user"){
            userRoute(userService)
        }
    }
}