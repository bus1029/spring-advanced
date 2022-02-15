package hello.springadvanced.proxy.common.advice

import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeAdvice : MethodInterceptor {
  private val log: Logger = LoggerFactory.getLogger(TimeAdvice::class.java)

  override fun invoke(invocation: MethodInvocation): Any? {
    log.info("Execute TimeAdvice")
    val start = System.currentTimeMillis()

    val result = invocation.proceed()

    val end = System.currentTimeMillis()
    val resultTime = end - start
    log.info("End TimeAdvice resultTime={}", resultTime)
    return result
  }
}