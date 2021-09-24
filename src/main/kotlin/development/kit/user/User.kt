package development.kit.user

open class User(
    open var username: String,
    open var password: String,
)
{
    val uniqueId: Long ? = null
}