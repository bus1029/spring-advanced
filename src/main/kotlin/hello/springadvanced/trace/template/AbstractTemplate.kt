package hello.springadvanced.trace.template

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

abstract class AbstractTemplate<T> constructor(
  private val trace: LogTrace
) {

  fun execute(message: String): T {
    var status: TraceStatus? = null
    try {
      status = trace.begin(message)
      val result: T = call()
      trace.end(status)
      return result
    } catch (e: Exception)  {
      trace.exception(status!!, e)
      throw e
    }
  }

  abstract fun call(): T
}