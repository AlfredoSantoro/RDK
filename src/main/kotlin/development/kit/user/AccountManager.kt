package development.kit.user

import development.kit.exception.LoginException
import org.apache.commons.codec.digest.DigestUtils

class AccountManager(
    private val iUserStorage: IUserStorage
)
{
    fun updateAccount(accountToUpdate: Account, newAccount: UpdateAccount): Account
    {
        accountToUpdate.name = newAccount.name
        accountToUpdate.surname = newAccount.surname
        accountToUpdate.email = newAccount.email
        accountToUpdate.accountType = newAccount.accountType
        accountToUpdate.username = newAccount.username
        accountToUpdate.password = DigestUtils.sha256Hex(newAccount.password)
        return accountToUpdate
    }

    fun createAccount(newAccount: CreateAccount): Account
    {
        return Account(newAccount.name, newAccount.surname, newAccount.email, newAccount.username,
            newAccount.decodedPassword, newAccount.accountType)
    }

    fun checkLoginData(submittedLoginData: User, accountDataToCompare: Account): Account
    {
        val loginUserPasswordEncoded = DigestUtils.sha256Hex(submittedLoginData.password)
        if ( submittedLoginData.username == accountDataToCompare.username && loginUserPasswordEncoded == accountDataToCompare.password )
        {
            return accountDataToCompare
        }
        throw LoginException("incorrect login data")
    }
}