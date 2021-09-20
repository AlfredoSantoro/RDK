package development.kit.user.type

enum class UserType
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