import development.kit.identifier.TagNFCManager
import org.junit.Assert
import org.junit.Test

class TagNFCManagerTest
{
    @Test
    fun `Should create a tagNFC`()
    {
        Assert.assertNotNull(TagNFCManager.createTagNFC("testTagNFC", "testvalue"))
    }

    @Test
    fun `Should update a tagNFC`()
    {
        val tagNFC = TagNFCManager.createTagNFC("testTagNFC", "testvalue")
        val tagNFCUpdated = TagNFCManager.updateTagNFC(tagNFC, "newname", "newvalue")
        Assert.assertNotNull(tagNFCUpdated)
        Assert.assertEquals(tagNFCUpdated.name, "newname")
        Assert.assertEquals(tagNFCUpdated.value, "newvalue")
    }
}