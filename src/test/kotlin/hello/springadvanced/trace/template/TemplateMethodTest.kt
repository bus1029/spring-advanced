package hello.springadvanced.trace.template

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemplateMethodTest {
  private val logger: Logger = LoggerFactory.getLogger(TemplateMethodTest::class.java)

  @Test
  fun templateMethodV() {
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

  /**
   * Adjust Template Method Pattern
   */
  @Test
  fun templateMethodV1() {
    val template1: AbstractTemplate = SubClassLogic1()
    template1.execute()

    val template2: AbstractTemplate = SubClassLogic2()
    template2.execute()
  }

  /**
   * Adjust Template Method Pattern
   */
  @Test
  fun templateMethodV2() {
    val template1 = object : AbstractTemplate() {
      override fun call() {
        logger.info("Business Logic1")
      }
    }
    logger.info("template1={}", template1.javaClass)
    template1.execute()

    val template2 = object : AbstractTemplate() {
      override fun call() {
        logger.info("Business Logic2")
      }
    }
    logger.info("template2={}", template2.javaClass)
    template2.execute()
  }
}