package example.com.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    /*
    Has two tasks:\
    1. Negotiates between client and server
    2. Serializes/deserializes in a specific format (in this case, json)
     */
    install(ContentNegotiation) {
        json()
    }
}