package development.kit.user

import org.apache.commons.codec.digest.DigestUtils

class Account(
    name: String,
    surname: String,
    email: String,
    username: String,
    password: String,
    var accountType: AccountType
): User(username, password, name, surname, email)
{
    init
    {
        this.password = DigestUtils.sha256Hex(this.password)
    }
}