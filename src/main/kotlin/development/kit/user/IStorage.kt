package development.kit.user

interface IStorage
{
    fun updateAccount(account: Account): Account
    fun findAccountById(accountId: Long): Account?
    fun findAccountByUsername(username: String): Account?
    fun saveAccount(createAccount: CreateAccount): Account
}