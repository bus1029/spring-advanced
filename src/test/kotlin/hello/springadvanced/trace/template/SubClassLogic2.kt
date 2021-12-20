package hello.springadvanced.trace.template

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SubClassLogic2 : AbstractTemplate() {
  private val logger: Logger = LoggerFactory.getLogger(SubClassLogic2::class.java)

  override fun call() {
    logger.info("Business Logic2")
  }
}