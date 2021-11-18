package hello.springadvanced.app.v2

import hello.springadvanced.trace.TraceId
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.hellotrace.HelloTraceV2
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV2(private val helloTrace: HelloTraceV2) {
  val logger = LoggerFactory.getLogger(OrderRepositoryV2::class.java)

  fun save(itemId1: TraceId, itemId: String) {
    var status: TraceStatus? = null
    try {
      status = helloTrace.beginSync(itemId1,"OrderRepository1.save()")
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