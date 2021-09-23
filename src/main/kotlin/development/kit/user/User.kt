package development.kit.user

abstract class User(
    open var username: String,
    open var password: String,
)
{
    val uniqueId: Long ? = null
}