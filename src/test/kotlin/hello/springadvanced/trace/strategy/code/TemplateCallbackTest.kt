package hello.springadvanced.trace.strategy.code

import hello.springadvanced.trace.strategy.code.template.Callback
import hello.springadvanced.trace.strategy.code.template.TimeLogTemplate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TemplateCallbackTest {
  val logger: Logger = LoggerFactory.getLogger(TemplateCallbackTest::class.java)

  @Test
  @DisplayName("템플릿 콜백 패턴 - 익명 내부 클래스")
  fun callbackV1() {
    val timeLogTemplate = TimeLogTemplate()
    timeLogTemplate.call(object : Callback {
      override fun call() {
        logger.info("Business Logic1")
      }
    })

    timeLogTemplate.call(object : Callback {
      override fun call() {
        logger.info("Business Logic2")
      }
    })
  }

  @Test
  @DisplayName("템플릿 콜백 패턴 - 람다")
  fun callbackV2() {
    val timeLogTemplate = TimeLogTemplate()
    timeLogTemplate.call {
      logger.info("Business Logic1")
    }

    timeLogTemplate.call {
      logger.info("Business Logic2")
    }
  }
}