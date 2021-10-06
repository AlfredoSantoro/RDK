package development.kit.user

import development.kit.exception.AccountNotFoundException
import development.kit.exception.IllegalAccountException

/**
 * AccountManagerStorage è una classe che si prende cura di tutte quelle logiche di persistenza e recupero dei dati
 * relativamente alla classe Account
 */
class AccountManagerStorage(
    private val storage: IStorage
)
{
    /**
     * Aggiorna i dati di un account esistente e li rende persistenti
     * @param updateAccount nuovi dati dell'account
     */
    @Throws(AccountNotFoundException::class)
    fun updateAnExistingAccountAndStoreIt(updateAccount: UpdateAccount): Account
    {
        val accountFounded = this.findAccount(updateAccount.id)
        return if ( accountFounded !== null )
        {
            val accountUpdated = AccountManagerLogic.updateAccount(accountFounded, updateAccount)
            this.updateAccount(accountUpdated)
        } else throw AccountNotFoundException("account #${updateAccount.id} not found")
    }

    /**
     * Registra un nuovo account
     * @param createAccount i dati del nuovo account
     */
    @Throws(IllegalAccountException::class)
    fun signUpANewAccount(createAccount: CreateAccount): Account
    {
        return if ( this.findAccount(createAccount.username) == null )
        {
            val accountSaved = this.saveAccount(createAccount)
            accountSaved
        }
        else
        {
            throw IllegalAccountException("account #${createAccount.username} already exist")
        }
    }

    /**
     * Persiste i dati aggiornati di un account
     * @param account l'account aggiornato
     */
    fun updateAccount(account: Account): Account { return this.storage.updateAccount(account) }

    /**
     * Recupera i dati di un account ricercando esso per id
     * @param id dell'account
     */
    fun findAccount(id: Long): Account? { return this.storage.findAccountById(id) }

    /**
     * Recupera i dati di un account ricercando esso per username
     * @param username dell'account
     */
    fun findAccount(username: String): Account? { return this.storage.findAccountByUsername(username) }

    /**
     * Persiste un nuovo account
     * @param createAccount i dati del nuovo account
     */
    fun saveAccount(createAccount: CreateAccount): Account { return this.storage.saveAccount(createAccount) }
}