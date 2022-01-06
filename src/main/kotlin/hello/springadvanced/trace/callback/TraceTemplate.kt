package hello.springadvanced.trace.callback

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TraceTemplate constructor(
  private val trace: LogTrace
) {
  val log: Logger = LoggerFactory.getLogger(TraceTemplate::class.java)

  fun <T> execute(message: String, callback: TraceCallback<T>): T {
    var status: TraceStatus? = null
    try {
      status = trace.begin(message)
      val result: T = callback.call()
      trace.end(status)
      return result
    } catch (e: Exception)  {
      trace.exception(status!!, e)
      throw e
    }
  }
}