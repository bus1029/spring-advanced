package hello.springadvanced.proxy.config.v2_dynamicproxy.handler

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.springframework.util.PatternMatchUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogTraceFilterHandler(
  private val target: Any,
  private val logTrace: LogTrace,
  private val patterns: Array<String>
): InvocationHandler {
  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
    // 메서드 이름 필터
    val methodName = method!!.name
    // save, request, reque*, *est, ...
    if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
      return method.invoke(target, *(args ?: arrayOfNulls(0)))
    }

    var status: TraceStatus? = null
    try {
      val message = method.declaringClass.simpleName + "." + method.name + "()"
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