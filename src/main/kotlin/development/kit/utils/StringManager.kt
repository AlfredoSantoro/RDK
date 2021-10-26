package development.kit.utils

object StringManager
{
    fun areEquals(s1: String, s2: String): Boolean
    {
        return s1.compareTo(s2) == 0
    }
}