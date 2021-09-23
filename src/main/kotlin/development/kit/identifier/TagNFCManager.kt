package development.kit.identifier

class TagNFCManager
{
    fun updateTagNFC(tagNFCToUpdate: TagNFC, newName: String, newValue: String): TagNFC
    {
        tagNFCToUpdate.name = newName
        tagNFCToUpdate.value = newValue
        return tagNFCToUpdate
    }

    fun createTagNFC(name: String, value: String): TagNFC
    {
        return TagNFC(name, value)
    }
}