package development.kit.user

interface IUserStorage
{
    fun findUser(id: Any): Account?
}