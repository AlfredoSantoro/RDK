package development.kit.user

import development.kit.exception.IllegalAccountException

class AccountManagerStorage(
    private val storage: IStorage
)
{
    @Throws(IllegalAccountException::class)
    fun findAccountUpdateAndStoreIt(updateAccount: UpdateAccount): Account
    {
        val accountFounded = this.findAccount(updateAccount.id)
        return if ( accountFounded !== null )
        {
            val accountUpdated = AccountManagerLogic.updateAccount(accountFounded, updateAccount)
            this.storage.updateAccount(accountUpdated)
        } else throw IllegalAccountException("account with id #${updateAccount.id} not found")
    }

    @Throws(IllegalAccountException::class)
    fun signUpAccountIfItDoesNotAlreadyExist(createAccount: CreateAccount): Account
    {
        return if ( this.findAccount(createAccount.username) == null )
        {
            val accountSaved = this.saveAccount(createAccount)
            accountSaved
        }
        else
        {
            throw IllegalAccountException("account with username #${createAccount.username} already exist")
        }
    }

    fun findAccount(id: Long): Account? { return this.storage.findAccountById(id) }

    fun findAccount(username: String): Account? { return this.storage.findAccountByUsername(username) }

    fun saveAccount(createAccount: CreateAccount): Account { return this.storage.saveAccount(createAccount) }
}