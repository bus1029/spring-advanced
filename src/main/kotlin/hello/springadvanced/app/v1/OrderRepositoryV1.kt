package hello.springadvanced.app.v1

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV1
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV1(private val helloTrace: HelloTraceV1) {
  val logger = LoggerFactory.getLogger(OrderRepositoryV1::class.java)

  fun save(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = helloTrace.begin("OrderRepository1.save()")
      if ("ex" == itemId) {
        throw IllegalArgumentException("Exception occurred!")
      }
      sleep(1000)
      helloTrace.end(status)
    } catch (e: Exception) {
      status?.let {
        helloTrace.exception(status, e)
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