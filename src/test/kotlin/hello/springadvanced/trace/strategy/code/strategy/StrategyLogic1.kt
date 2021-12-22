package hello.springadvanced.trace.strategy.code.strategy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StrategyLogic1 : Strategy {
  val logger: Logger = LoggerFactory.getLogger(StrategyLogic1::class.java)

  override fun call() {
    logger.info("Business Logic1")
  }
}