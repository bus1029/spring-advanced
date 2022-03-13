package hello.springadvanced.aop.internalcall.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class CallLogAspect {
  private val log: Logger = LoggerFactory.getLogger(CallLogAspect::class.java)

  @Before("execution(* hello.springadvanced.aop.internalcall..*.*(..))")
  fun doLog(joinPoint: JoinPoint) {
    log.info("aop={}", joinPoint.signature)
  }
}