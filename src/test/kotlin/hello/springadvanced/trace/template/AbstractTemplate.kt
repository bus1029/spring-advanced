package hello.springadvanced.trace.template

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class AbstractTemplate {
  private val logger: Logger = LoggerFactory.getLogger(AbstractTemplate::class.java)

  fun execute() {
    val startTime = System.currentTimeMillis()
    call()
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }

  abstract fun call()
}