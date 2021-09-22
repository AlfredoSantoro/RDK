package development.kit.user

data class UpdateAccount(
    val name: String,
    val surname: String,
    val email: String,
    val username: String,
    val password: String,
    val accountType: AccountType
)