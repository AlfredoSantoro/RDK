package development.kit.utils

import org.apache.commons.codec.digest.DigestUtils

object PasswordManager
{
    fun encodePassword(plainPassword: String): String
    {
        return DigestUtils.sha256Hex(plainPassword)
    }
}