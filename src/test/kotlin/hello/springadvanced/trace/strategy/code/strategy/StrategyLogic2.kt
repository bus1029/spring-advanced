package hello.springadvanced.trace.strategy.code.strategy

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StrategyLogic2 : Strategy {
  val logger: Logger = LoggerFactory.getLogger(StrategyLogic2::class.java)

  override fun call() {
    logger.info("Business Logic2")
  }
}