package hello.springadvanced.trace.logtrace

import hello.springadvanced.trace.TraceStatus
import java.lang.Exception

interface LogTrace {
  fun begin(message: String): TraceStatus
  fun end(status: TraceStatus)
  fun exception(status: TraceStatus, e: Exception)
}