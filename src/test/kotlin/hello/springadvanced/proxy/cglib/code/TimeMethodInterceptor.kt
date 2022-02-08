package hello.springadvanced.proxy.cglib.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cglib.proxy.MethodInterceptor
import org.springframework.cglib.proxy.MethodProxy
import java.lang.reflect.Method

open class TimeMethodInterceptor(
  private val target: Any
): MethodInterceptor {
  private val log: Logger = LoggerFactory.getLogger(TimeMethodInterceptor::class.java)

  override fun intercept(obj: Any?, method: Method?, args: Array<out Any>?, methodProxy: MethodProxy?): Any? {
    log.info("Execute TimeProxy")
    val start = System.currentTimeMillis()

    val result = methodProxy!!.invoke(target, args)

    val end = System.currentTimeMillis()
    val resultTime = end - start
    log.info("End TimeProxy resultTime={}", resultTime)
    return result
  }
}