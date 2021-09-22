package development.kit.user

import org.apache.commons.codec.digest.DigestUtils

data class Account(
    var name: String,
    var surname: String,
    var email: String,
    override var username: String,
    override var password: String,
    val accountId: Long,
    var accountType: AccountType
): User(name, surname, accountId)

{
    init
    {
        this.password = DigestUtils.sha256Hex(this.password)
    }
}