package development.kit.identifier

data class TagNFC(
    var name: String,
    var value: String
): Identifier(value)