package development.kit.user

/**
 * AccountManagerLogic è una classe che si prende cura delle logiche relative alla classe Account
 */
object AccountManagerLogic
{
    /**
     * Aggiorna i dati di un account
     * @param accountToUpdate l'account che deve essere aggiornato
     * @param newAccount i nuovi dati dell'account
     */
    fun updateAccount(accountToUpdate: Account, newAccount: UpdateAccount): Account
    {
        accountToUpdate.name = newAccount.name
        accountToUpdate.surname = newAccount.surname
        accountToUpdate.email = newAccount.email
        accountToUpdate.accountType = newAccount.accountType
        accountToUpdate.username = newAccount.username
        accountToUpdate.password = newAccount.password
        return accountToUpdate
    }

    /**
     * Crea un account
     * @param newAccount i dati del nuovo account
     */
    fun createAccount(newAccount: CreateAccount): Account
    {
        return Account(newAccount.name, newAccount.surname, newAccount.email, newAccount.username,
            newAccount.password, newAccount.accountType)
    }
}