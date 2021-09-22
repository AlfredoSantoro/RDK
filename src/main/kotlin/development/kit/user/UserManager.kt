package development.kit.user

import org.apache.commons.codec.digest.DigestUtils

class UserManager
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
}