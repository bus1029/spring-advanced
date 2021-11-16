package hello.springadvanced.trace.hellotrace

import hello.springadvanced.trace.TraceId
import hello.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.Exception
import java.lang.StringBuilder

@Component
class HelloTraceV1 {
  val logger = LoggerFactory.getLogger(HelloTraceV1::class.java)

  companion object {
    private const val START_PREFIX = "-->"
    private const val COMPLETE_PREFIX = "<--"
    private const val EX_PREFIX = "<X-"

    // level=0
    // level=1 |-->
    // level=2 |   |-->
    // level=2 ex |   |<X-
    // level=1 ex |<X-
    private fun addSpace(startPrefix: String, level: Int): String {
      val stringBuilder = StringBuilder()
      for (i in 0 until level) {
        if (i == level-1) {
          stringBuilder.append("|$startPrefix")
        } else {
          stringBuilder.append("|   ")
        }
      }
      return stringBuilder.toString()
    }
  }

  fun begin(message: String): TraceStatus {
    val traceId = TraceId()
    val startTimeMs = System.currentTimeMillis()
    logger.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
    return TraceStatus(traceId, startTimeMs, message)
  }

  fun end(status: TraceStatus) {
    complete(status, null)
  }

  fun exception(status: TraceStatus, exception: Exception) {
    complete(status, exception)
  }

  private fun complete(status: TraceStatus, exception: Exception?) {
    val stopTimeMs = System.currentTimeMillis()
    val resultTimeMs = stopTimeMs - status.startTimeMs
    val traceId = status.traceId
    if (exception == null) {
      logger.info("[{}] {}{} time={}ms", traceId.id, addSpace(COMPLETE_PREFIX, traceId.level),
        status.message, resultTimeMs)
    } else {
      logger.info("[{}] {}{} time={}ms ex={}", traceId.id, addSpace(EX_PREFIX, traceId.level),
        status.message, resultTimeMs, exception.toString())
    }
  }
}