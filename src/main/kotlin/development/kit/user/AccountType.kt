package development.kit.user

enum class AccountType
{
    // users registered by the system
    ADMIN,
    // users registered by admin
    GUEST,
    // registered users via app
    USER,
    // everything else
    UNKNOWN
}