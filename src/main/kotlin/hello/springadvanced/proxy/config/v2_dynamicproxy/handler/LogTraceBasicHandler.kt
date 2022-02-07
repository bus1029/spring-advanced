package hello.springadvanced.proxy.config.v2_dynamicproxy.handler

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceBasicHandler(
  private val target: Any,
  private val logTrace: LogTrace
): InvocationHandler {
  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
    var status: TraceStatus? = null
    try {
      val message = method!!.declaringClass.simpleName + "." + method.name + "()"
      status = logTrace.begin(message)

      // 로직 호출
      val result = method.invoke(target, *(args ?: arrayOfNulls(0)))
      logTrace.end(status)
      return result
    } catch (e: Exception) {
      logTrace.exception(status!!, e)
      throw e
    }
  }
}