package hello.springadvanced.trace.threadlocal

import hello.springadvanced.trace.threadlocal.code.ThreadLocalService
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class ThreadLocalServiceTest {
  private val logger = LoggerFactory.getLogger(ThreadLocalServiceTest::class.java)
  private val service = ThreadLocalService()

  @Test
  fun threadSafeTest() {
    logger.info("main start")
    val userA: () -> Unit = {
      service.logic("userA")
    }
    val userB: () -> Unit = {
      service.logic("userB")
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
      service.logic("userA")
    }
    val userB: () -> Unit = {
      service.logic("userB")
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