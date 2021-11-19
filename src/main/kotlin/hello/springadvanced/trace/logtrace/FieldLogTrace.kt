package hello.springadvanced.trace.logtrace

import hello.springadvanced.trace.TraceId
import hello.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class FieldLogTrace : LogTrace {
  val logger = LoggerFactory.getLogger(FieldLogTrace::class.java)
  companion object {
    private const val START_PREFIX = "-->"
    private const val COMPLETE_PREFIX = "<--"
    private const val EX_PREFIX = "<X-"

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

  // traceId 동기화, 동시성 이슈 발생
  private var traceIdHolder: TraceId? = null

  override fun begin(message: String): TraceStatus {
    syncTraceId()
    val traceId = traceIdHolder
    val startTimeMs = System.currentTimeMillis()
    logger.info("[{}] {}{}", traceId!!.id, addSpace(START_PREFIX, traceId.level), message)
    return TraceStatus(traceId, startTimeMs, message)
  }

  private fun syncTraceId() {
    traceIdHolder = if (traceIdHolder == null) {
      TraceId()
    } else {
      traceIdHolder!!.createNextId()
    }
  }

  override fun end(status: TraceStatus) {
    complete(status, null)
  }

  override fun exception(status: TraceStatus, e: Exception) {
    complete(status, e)
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

    releaseTraceId()
  }

  private fun releaseTraceId() {
    traceIdHolder?.let {
      traceIdHolder = if (traceIdHolder!!.isFirstLevel()) {
        null // destroy
      } else {
        traceIdHolder!!.createPreviousId()
      }
    }
  }
}