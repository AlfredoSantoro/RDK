import development.kit.authorization.AuthorizationManager
import development.kit.authorization.IAuthorization
import development.kit.authorization.UpdateAuthorization
import development.kit.exception.IllegalAuthorizationException
import development.kit.user.AccountManager
import development.kit.user.AccountType
import development.kit.user.CreateAccount
import org.junit.Assert
import org.junit.Test
import java.time.OffsetDateTime

class AuthorizationManagerTest
{
    @Test
    fun `Should create an authorization`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val auth = AuthorizationManager.createAuthorization(OffsetDateTime.now(), OffsetDateTime.now().plusDays(30),
            newAccount)
        Assert.assertNotNull(auth)
    }

    @Test(expected = IllegalAuthorizationException::class)
    fun `Should not create an authorization`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val authorizationStart = OffsetDateTime.now()
        val authorizationEnd = authorizationStart.minusDays(2)
        AuthorizationManager.createAuthorization(authorizationStart, authorizationEnd, newAccount)
    }

    @Test
    fun `Should update an authorization`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val startAuthorization = OffsetDateTime.now()
        val endAuthorization = OffsetDateTime.now().plusDays(30)
        val auth = AuthorizationManager.createAuthorization(startAuthorization, endAuthorization, newAccount)
        Assert.assertNotNull(auth)
        val reason = "research thesis"
        val newAuth = AuthorizationManager.updateAuthorization(UpdateAuthorization(startAuthorization.plusDays(1),
            endAuthorization, reason), auth)
        Assert.assertNotNull(newAuth)
        Assert.assertEquals(newAuth.start, startAuthorization.plusDays(1))
        Assert.assertEquals(newAuth.reason, reason)
    }

    @Test
    fun `Should tell you that the user has authorization because it was approved`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val startAuthorization = OffsetDateTime.now()
        val endAuthorization = OffsetDateTime.now().plusDays(30)
        val auth = AuthorizationManager.createAuthorization(startAuthorization, endAuthorization, newAccount)
        AuthorizationManager.approveAuthorization(auth)
        Assert.assertNotNull(auth)
        Assert.assertTrue(AuthorizationManager.userHaveAuthorization(auth))
    }

    @Test
    fun `Should tell you that the user does not have authorization because it has not been processed`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val startAuthorization = OffsetDateTime.now()
        val endAuthorization = OffsetDateTime.now().plusDays(30)
        val auth = AuthorizationManager.createAuthorization(startAuthorization, endAuthorization, newAccount)
        Assert.assertNotNull(auth)
        Assert.assertFalse(AuthorizationManager.userHaveAuthorization(auth))
    }

    @Test
    fun `Should tell you that the user does not have authorization because it was rejected`()
    {
        val createAccount = CreateAccount("testAccountName", "testAccountSurname",
            "fakeemail@test.it", "usernameTest", "testpass", AccountType.USER
        )
        val newAccount = AccountManager.createAccount(createAccount)
        Assert.assertNotNull(newAccount)
        val startAuthorization = OffsetDateTime.now()
        val endAuthorization = OffsetDateTime.now().plusDays(30)
        val auth = AuthorizationManager.createAuthorization(startAuthorization, endAuthorization, newAccount)
        AuthorizationManager.rejectAuthorization(auth)
        Assert.assertNotNull(auth)
        Assert.assertFalse(AuthorizationManager.userHaveAuthorization(auth))
    }

    @Test
    fun `Should tell you that there are no overlapping authorizations`()
    {
        val authorizationOverlapsNumber = 0
        val start = OffsetDateTime.now()
        val end = OffsetDateTime.now()
        Assert.assertFalse(AuthorizationManager.areAuthorizationsOverlaps(object : IAuthorization {
            override fun findUserAuthorizationOverlaps(start: OffsetDateTime, end: OffsetDateTime, userID: Long): Int {
                return authorizationOverlapsNumber
            }
        }, start, end, -1))
    }

    @Test
    fun `Should tell you that there are overlapping authorizations`()
    {
        val authorizationOverlapsNumber = 5
        val start = OffsetDateTime.now()
        val end = OffsetDateTime.now()
        Assert.assertTrue(AuthorizationManager.areAuthorizationsOverlaps(object : IAuthorization {
            override fun findUserAuthorizationOverlaps(start: OffsetDateTime, end: OffsetDateTime, userID: Long): Int {
                return authorizationOverlapsNumber
            }
        }, start, end, -1))
    }
}