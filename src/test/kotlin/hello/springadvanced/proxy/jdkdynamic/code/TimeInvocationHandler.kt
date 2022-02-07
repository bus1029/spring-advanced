package hello.springadvanced.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
  private val target: Any
): InvocationHandler {
  private val log: Logger = LoggerFactory.getLogger(TimeInvocationHandler::class.java)

  override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
    log.info("Execute TimeProxy")
    val start = System.currentTimeMillis()

//    val result = method?.invoke(target, args)
    val result = method?.invoke(target, *(args ?: arrayOfNulls(0)))

    val end = System.currentTimeMillis()
    val resultTime = end - start
    log.info("End TimeProxy resultTime={}", resultTime)
    return result
  }
}