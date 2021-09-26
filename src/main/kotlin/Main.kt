import java.util.logging.Logger

fun main()
{
    val logger: Logger = Logger.getLogger("Main Logger")
    val javaVersion = System.getProperty("java.version")
    logger.info("***********************************************")
    logger.info("RDK - RESERVATION DEVELOPMENT KIT")
    logger.info("Running on JVM v.$javaVersion")
    logger.info("***********************************************")
}