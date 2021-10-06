import development.kit.user.AccountManagerLogic
import development.kit.user.AccountType
import development.kit.user.CreateAccount
import development.kit.user.UpdateAccount
import development.kit.utils.PasswordManager
import org.junit.Assert
import org.junit.Test

class UserManagerTest
{
    @Test
    fun `Should create an account`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManagerLogic.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
    }

    @Test
    fun `Should update an account with new email and username`()
    {
        val decodedPassword = "testpass"
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", decodedPassword, AccountType.USER
        )
        val newAccount = AccountManagerLogic.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val updateAccount = UpdateAccount(-1, createAccount.name, createAccount.surname, "newemail", "newusername",
            decodedPassword, newAccount.accountType
        )
        updateAccount.password = PasswordManager.encodePassword(updateAccount.password)
        val accountUpdated = AccountManagerLogic.updateAccount(newAccount, updateAccount)
        Assert.assertNotNull(accountUpdated)
        Assert.assertEquals(accountUpdated.email, updateAccount.email)
        Assert.assertEquals(accountUpdated.username, updateAccount.username)
        Assert.assertEquals(accountUpdated.password, updateAccount.password)
    }

    @Test
    fun `Should update an account with new password`()
    {
        val decodedPassword = "testpass"
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", decodedPassword, AccountType.USER
        )
        val newAccount = AccountManagerLogic.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val updateAccount = UpdateAccount(-1, createAccount.name, createAccount.surname, createAccount.email, createAccount.username,
            "newpass", newAccount.accountType
        )
        updateAccount.password = PasswordManager.encodePassword(updateAccount.password)
        val accountUpdated = AccountManagerLogic.updateAccount(newAccount, updateAccount)
        Assert.assertNotNull(accountUpdated)
        Assert.assertEquals(accountUpdated.password, updateAccount.password)
    }
}