package hello.springadvanced.app.v3

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV3(private val trace: LogTrace) {
  val logger = LoggerFactory.getLogger(OrderRepositoryV3::class.java)

  fun save(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = trace.begin("OrderRepository1.save()")
      if ("ex" == itemId) {
        throw IllegalArgumentException("Exception occurred!")
      }
      sleep(1000)
      trace.end(status)
    } catch (e: Exception) {
      status?.let {
        trace.exception(status, e)
      }
      throw e
    }
  }

  fun sleep(milliSecond: Int) {
    try {
      Thread.sleep(milliSecond.toLong())
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}