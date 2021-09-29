package development.kit.user

data class CreateAccount(
    val name: String,
    val surname: String,
    val email: String,
    val username: String,
    var password: String,
    val accountType: AccountType
)