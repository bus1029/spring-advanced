package hello.springadvanced.trace.threadlocal

import hello.springadvanced.trace.threadlocal.code.FieldService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class FieldServiceTest {
  private val logger = LoggerFactory.getLogger(FieldServiceTest::class.java)
  private val fieldService = FieldService()

  @Test
  fun threadSafeTest() {
    logger.info("main start")
    val userA: () -> Unit = {
      fieldService.logic("userA")
    }
    val userB: () -> Unit = {
      fieldService.logic("userB")
    }

    val threadA = Thread(userA)
    threadA.name = "thread-A"
    val threadB = Thread(userB)
    threadB.name = "thread-B"

    threadA.start()
    sleep(2000)
    threadB.start()
    threadB.join()
  }

  @Test
  fun threadUnsafeTest() {
    logger.info("main start")
    val userA: () -> Unit = {
      fieldService.logic("userA")
    }
    val userB: () -> Unit = {
      fieldService.logic("userB")
    }

    val threadA = Thread(userA)
    threadA.name = "thread-A"
    val threadB = Thread(userB)
    threadB.name = "thread-B"

    threadA.start()
    threadB.start()
    threadB.join()
  }

  private fun sleep(millis: Long) {
    try {
      Thread.sleep(millis)
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}