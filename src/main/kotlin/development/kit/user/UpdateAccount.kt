package development.kit.user

data class UpdateAccount(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val username: String,
    val password: String,
    val accountType: AccountType
)