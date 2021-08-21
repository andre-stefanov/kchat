package repositories

import com.jambit.kchat.android.rest.RestClient
import com.jambit.kchat.model.User

class UserRepository(
    private val client: RestClient
) {

    suspend fun getUsers() : List<User> = client.getUsers()

}