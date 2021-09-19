package development.kit.identifier

data class TagNFC(
    val name: String,
    val value: String
): Identifier(value)