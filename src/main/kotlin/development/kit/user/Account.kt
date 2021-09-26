package development.kit.user

import development.kit.utils.PasswordManager

open class Account(
    name: String,
    surname: String,
    email: String,
    username: String,
    password: String,
    var accountType: AccountType,
    var accountId: Long ? = null
): User(username, password, name, surname, email)
{
    init
    {
        this.password = PasswordManager.encodePassword(this.password)
        println("encoded account password")
    }

    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        AccountType.UNKNOWN
    )
}