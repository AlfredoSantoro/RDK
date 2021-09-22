package development.kit.identifier

class TagNFCManager
{
    fun updateTagNFC(tagNFC: TagNFC, newName: String, newValue: String): TagNFC
    {
        tagNFC.name = newName
        tagNFC.value = newValue
        return tagNFC
    }
}