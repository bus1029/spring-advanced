package hello.springadvanced.trace.logtrace

import hello.springadvanced.trace.TraceId
import hello.springadvanced.trace.TraceStatus
import org.slf4j.LoggerFactory

class ThreadLocalLogTrace : LogTrace {
  private val logger = LoggerFactory.getLogger(ThreadLocalLogTrace::class.java)
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

  private val traceIdHolder = ThreadLocal<TraceId>()

  override fun begin(message: String): TraceStatus {
    syncTraceId()
    val traceId = traceIdHolder.get()
    val startTimeMs = System.currentTimeMillis()
    logger.info("[{}] {}{}", traceId.id, addSpace(START_PREFIX, traceId.level), message)
    return TraceStatus(traceId, startTimeMs, message)
  }

  private fun syncTraceId() {
    val traceId = traceIdHolder.get()
    if (traceId == null) {
      traceIdHolder.set(TraceId())
    } else {
      traceIdHolder.set(traceId.createNextId())
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
    val traceId = traceIdHolder.get()
    if (traceId.isFirstLevel()) {
      traceIdHolder.remove()
    } else {
      traceIdHolder.set(traceId.createPreviousId())
    }
  }
}