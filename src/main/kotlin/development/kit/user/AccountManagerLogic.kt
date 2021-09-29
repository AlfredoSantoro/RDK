package development.kit.user

import development.kit.exception.LoginException

object AccountManagerLogic
{
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

    fun createAccount(newAccount: CreateAccount): Account
    {
        return Account(newAccount.name, newAccount.surname, newAccount.email, newAccount.username,
            newAccount.password, newAccount.accountType)
    }

    @Throws(LoginException::class)
    fun checkLoginData(submittedLoginData: LoginData, accountDataToCompare: Account): Account
    {
        if ( submittedLoginData.username == accountDataToCompare.username && submittedLoginData.encodedPassword == accountDataToCompare.password )
        {
            return accountDataToCompare
        }
        throw LoginException("incorrect login data")
    }
}