package hello.springadvanced.aop.exam.aop

import hello.springadvanced.aop.exam.annotation.Retry
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class RetryAspect {
  private val log: Logger = LoggerFactory.getLogger(RetryAspect::class.java)

  @Around("@annotation(retry)")
  fun doRetry(joinPoint: ProceedingJoinPoint, retry: Retry): Any? {
    log.info("[retry] {} retry={}", joinPoint.signature, retry)

    val maxRetry = retry.value
    var exceptionHolder: Exception? = null
    for (retryCount in 1..maxRetry) {
      try {
        log.info("[retry] try count={}/{}", retryCount, maxRetry)
        return joinPoint.proceed()
      } catch (e: Exception) {
        exceptionHolder = e
      }
    }
    throw exceptionHolder!!
  }
}