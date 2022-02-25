package hello.springadvanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV2 {
  private val log: Logger = LoggerFactory.getLogger(AspectV2::class.java)

  // hello.springadvanced.aop.order 패키지와 하위 패키지
  @Pointcut("execution(* hello.springadvanced.aop.order..*(..))")
  private fun allOrder() {} // Pointcut signature

  @Around("allOrder()")
  fun doLog(joinPoint: ProceedingJoinPoint): Any? {
    log.info("[log] {}", joinPoint.signature)
    return joinPoint.proceed()
  }
}