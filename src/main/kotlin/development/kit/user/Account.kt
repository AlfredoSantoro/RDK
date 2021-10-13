package development.kit.user

open class Account(
    var name: String,
    var surname: String,
    var email: String,
    username: String,
    password: String,
    var accountType: AccountType
): User(username, password)
{
    var accountId: Long ? = null
}