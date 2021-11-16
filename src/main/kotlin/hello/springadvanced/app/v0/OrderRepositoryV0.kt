package hello.springadvanced.app.v0

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV0 {
  val logger = LoggerFactory.getLogger(OrderRepositoryV0::class.java)

  fun save(itemId: String) {
    if ("ex" == itemId) {
      throw IllegalArgumentException("Exception occurred!")
    }
    sleep(1000)
  }

  fun sleep(milliSecond: Int) {
    try {
      Thread.sleep(milliSecond.toLong())
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}