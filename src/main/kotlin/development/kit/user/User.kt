package development.kit.user

import development.kit.Person
import org.apache.commons.codec.digest.DigestUtils

data class User(
    var userName: String,
    var userSurname: String,
    var email: String,
    var username: String,
    var password: String
): Person(userName, userSurname)

{
    init
    {
        this.password = DigestUtils.sha256Hex(this.password)
    }
}