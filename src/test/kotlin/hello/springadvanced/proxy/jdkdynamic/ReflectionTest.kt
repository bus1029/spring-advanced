package hello.springadvanced.proxy.jdkdynamic

import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Method
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.functions

class ReflectionTest {
  private val log: Logger = LoggerFactory.getLogger(ReflectionTest::class.java)

  @Test
  fun reflection0() {
    val target = Hello()

    log.info("start")
    val aResult = target.callA()
    log.info("result={}", aResult)

    log.info("start")
    val bResult = target.callB()
    log.info("result={}", bResult)
  }

  @Test
  fun reflection1() {
    // Class reference
    val helloClass = Hello::class.java

    val target = Hello()
    val methodCallA = helloClass.getMethod("callA")
    val result1 = methodCallA.invoke(target)
    log.info("result1={}", result1)

    val methodCallB = helloClass.getMethod("callB")
    val result2 = methodCallB.invoke(target)
    log.info("result2={}", result2)
  }

  @Test
  fun reflection2() {
    // Class reference
    val helloClass = Hello::class.java

    val target = Hello()
    val methodCallA = helloClass.getMethod("callA")
    dynamicCall(methodCallA, target)

    val methodCallB = helloClass.getMethod("callB")
    dynamicCall(methodCallB, target)
  }

  private fun dynamicCall(method: Method, target: Any) {
    log.info("start")
    val result = method.invoke(target)
    log.info("result={}", result)
  }

  class Hello {
    private val log: Logger = LoggerFactory.getLogger(Hello::class.java)

    fun callA(): String {
      log.info("CallA")
      return "A"
    }

    fun callB(): String {
      log.info("CallB")
      return "B"
    }
  }
}