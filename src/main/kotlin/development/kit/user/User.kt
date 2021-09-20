package development.kit.user

import development.kit.user.type.UserType
import org.apache.commons.codec.digest.DigestUtils

data class User(
    var userName: String,
    var userSurname: String,
    var email: String,
    var username: String,
    var password: String,
    val userId: Long,
    var userType: UserType
): Person(userName, userSurname, userId)

{
    init
    {
        this.password = DigestUtils.sha256Hex(this.password)
    }
}