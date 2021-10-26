import development.kit.utils.StringManager
import org.junit.Assert
import org.junit.Test

class StringManagerTest
{
    @Test
    fun `Should say the s1 and s2 string are the same`()
    {
        val s1 = "example-test"
        val s2 = "example-test"
        Assert.assertTrue(StringManager.areEquals(s1,s2))
    }

    @Test
    fun `Should say the s1 and s2 string are not the same`()
    {
        val s1 = "example-test1"
        val s2 = "example-test"
        Assert.assertFalse(StringManager.areEquals(s1,s2))
    }
}