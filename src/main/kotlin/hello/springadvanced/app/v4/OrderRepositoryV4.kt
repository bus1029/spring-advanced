package hello.springadvanced.app.v4

import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.template.AbstractTemplate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4(private val trace: LogTrace) {
  val logger = LoggerFactory.getLogger(OrderRepositoryV4::class.java)

  fun save(itemId: String) {
    val template = object : AbstractTemplate<Unit>(trace) {
      override fun call() {
        // Save logic
        if ("ex" == itemId) {
          throw IllegalArgumentException("Exception occurred!")
        }
        sleep(1000)
      }
    }
    return template.execute("OrderRepository.save()")
  }

  fun sleep(milliSecond: Int) {
    try {
      Thread.sleep(milliSecond.toLong())
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}