package hello.springadvanced.trace.strategy.code.strategy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 필드에 전략을 보관하는 방식
 */
class ContextV1 constructor() {
  private var strategy: Strategy? = null
  constructor(strategy: Strategy): this() {
    this.strategy = strategy
  }

  val logger: Logger = LoggerFactory.getLogger(ContextV1::class.java)

  fun execute() {
    val startTime = System.currentTimeMillis()
    strategy?.call() // Delegate
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }

  fun execute(strategy: () -> Unit) {
    val startTime = System.currentTimeMillis()
    strategy.invoke() // Delegate
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }
}