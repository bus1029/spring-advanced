package hello.springadvanced.proxy.config.v6_aop.aspect

import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class LogTraceAspect(
  private val logTrace: LogTrace
) {
  private val log: Logger = LoggerFactory.getLogger(LogTraceAspect::class.java)

  @Around("execution(* hello.springadvanced.proxy.app..*(..))")
  fun execute(joinPoint: ProceedingJoinPoint): Any? {
    var status: TraceStatus? = null

    log.info("target={}", joinPoint.target)
    log.info("getArgs={}", joinPoint.args)
    log.info("getSignature={}", joinPoint.signature)

    try {
      val message = joinPoint.signature.toShortString()
      status = logTrace.begin(message)

      // 로직 호출
      val result = joinPoint.proceed()

      logTrace.end(status)
      return result
    } catch (e: Exception) {
      logTrace.exception(status!!, e)
      throw e
    }
  }
}