package development.kit.identifier

data class TagNFC(
    var name: String,
    override var value: String
): Identifier(value)