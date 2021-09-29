package development.kit.user

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

    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        AccountType.UNKNOWN
    )
}