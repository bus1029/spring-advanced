package hello.springadvanced.trace.strategy.code

import hello.springadvanced.trace.strategy.code.strategy.ContextV1
import hello.springadvanced.trace.strategy.code.strategy.Strategy
import hello.springadvanced.trace.strategy.code.strategy.StrategyLogic1
import hello.springadvanced.trace.strategy.code.strategy.StrategyLogic2
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ContextV1Test {
  val logger: Logger = LoggerFactory.getLogger(ContextV1Test::class.java)

  @Test
  fun strategyV0Test() {
    logic1()
    logic2()
  }

  private fun logic1() {
    val startTime = System.currentTimeMillis()
    logger.info("Business Logic1")
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }

  private fun logic2() {
    val startTime = System.currentTimeMillis()
    logger.info("Business Logic2")
    val endTime = System.currentTimeMillis()
    logger.info("Result time={}", endTime-startTime)
  }

  @Test
  fun strategyV1Test() {
    val strategyLogic1 = StrategyLogic1()
    val contextV1 = ContextV1(strategyLogic1)
    contextV1.execute()

    val strategyLogic2 = StrategyLogic2()
    val contextV2 = ContextV1(strategyLogic2)
    contextV2.execute()
  }

  @Test
  fun strategyV2Test() {
    val strategyLogic1 = object : Strategy {
      override fun call() {
        logger.info("Business logic1")
      }
    }
    val contextV1 = ContextV1(strategyLogic1)
    logger.info("StrategyLogicV1 = {}", strategyLogic1.javaClass)
    contextV1.execute()

    val strategyLogic2 = object : Strategy {
      override fun call() {
        logger.info("Business logic2")
      }
    }
    val contextV2 = ContextV1(strategyLogic2)
    logger.info("StrategyLogicV2 = {}", strategyLogic2.javaClass)
    contextV2.execute()
  }

  @Test
  fun strategyV3Test() {
    val contextV1 = ContextV1(object : Strategy {
      override fun call() {
        logger.info("Business logic1")
      }
    })
    contextV1.execute()

    val contextV2 = ContextV1(object : Strategy {
      override fun call() {
        logger.info("Business logic2")
      }
    })
    contextV2.execute()
  }

  @Test
  fun strategyV4Test() {
    val contextV1 = ContextV1()
    contextV1.execute {
      logger.info("Business logic1")
    }

    contextV1.execute {
      logger.info("Business logic2")
    }
  }
}