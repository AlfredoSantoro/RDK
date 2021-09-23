package development.kit.identifier

abstract class Identifier(
     open var value: String
)
{
     open val uniqueId: Long ? = null
}