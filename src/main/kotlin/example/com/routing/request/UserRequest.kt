package example.com.routing.request

import kotlinx.serialization.Serializable

/*
Will only contain username and password
because the API client should not be responsible
for generating the identifier
 */
/*
As a result of this, the serialization plugin will automaticallyu
generate the implementation of k-serializer
 */
@Serializable
data class UserRequest(
    val username: String,
    val password: String,

) {



}