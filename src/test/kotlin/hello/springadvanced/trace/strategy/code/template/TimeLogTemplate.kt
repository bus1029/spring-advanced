package hello.springadvanced.trace.strategy.code.template

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeLogTemplate {
  val logger: Logger = LoggerFactory.getLogger(TimeLogTemplate::class.java)

  fun call(strategy: () -> Unit) {
    val startTime = System.currentTimeMillis()
    strategy.invoke() // Delegate
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }

  fun call(callback: Callback) {
    val startTime = System.currentTimeMillis()
    callback.call() // Delegate
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }
}