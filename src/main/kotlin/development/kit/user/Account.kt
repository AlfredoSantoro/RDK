package development.kit.user

import org.apache.commons.codec.digest.DigestUtils

data class Account(
    var name: String,
    var surname: String,
    var email: String,
    override var username: String,
    override var password: String,
    var accountType: AccountType
): User(name, surname)
{
    init
    {
        this.password = DigestUtils.sha256Hex(this.password)
    }
}