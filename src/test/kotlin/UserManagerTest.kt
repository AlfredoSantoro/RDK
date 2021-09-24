import development.kit.exception.LoginException
import development.kit.user.*
import org.apache.commons.codec.digest.DigestUtils
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
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
    }

    @Test
    fun `Should update an account with new email and username`()
    {
        val decodedPassword = "testpass"
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", decodedPassword, AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val updateAccount = UpdateAccount(createAccount.name, createAccount.surname, "newemail", "newusername",
            decodedPassword, newAccount.accountType
        )
        val accountUpdated = AccountManager.updateAccount(newAccount, updateAccount)
        Assert.assertNotNull(accountUpdated)
        Assert.assertEquals(accountUpdated.email, updateAccount.email)
        Assert.assertEquals(accountUpdated.username, updateAccount.username)
        Assert.assertEquals(accountUpdated.password, DigestUtils.sha256Hex(updateAccount.password))
    }

    @Test
    fun `Should update an account with new password`()
    {
        val decodedPassword = "testpass"
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", decodedPassword, AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val updateAccount = UpdateAccount(createAccount.name, createAccount.surname, createAccount.email, createAccount.username,
            "newpass", newAccount.accountType
        )
        val accountUpdated = AccountManager.updateAccount(newAccount, updateAccount)
        Assert.assertNotNull(accountUpdated)
        Assert.assertEquals(accountUpdated.password, DigestUtils.sha256Hex(updateAccount.password))
    }

    @Test(expected = LoginException::class)
    fun `Should fail login for wrong credentials`()
    {
        val account = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(account)
        Assert.assertNotNull(newAccount)
        val wrongLoginUserData = User("anotherusername", "anotherpass")
        AccountManager.checkLoginData(wrongLoginUserData, newAccount)
    }

    @Test(expected = LoginException::class)
    fun `Should fail login for empty credentials`()
    {
        val account = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(account)
        Assert.assertNotNull(newAccount)
        val wrongLoginUserData = User("", "")
        AccountManager.checkLoginData(wrongLoginUserData, newAccount)
    }

    @Test
    fun `Should be successful login`()
    {
        val decodedPass = "testpass"
        val account = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", decodedPass, AccountType.USER
        )
        val newAccount = AccountManager.createAccount(account)
        Assert.assertNotNull(newAccount)
        val loginData = User(newAccount.username, decodedPass)
        AccountManager.checkLoginData(loginData, newAccount)
    }
}