package hello.springadvanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV3 {
  private val log: Logger = LoggerFactory.getLogger(AspectV3::class.java)

  // hello.springadvanced.aop.order 패키지와 하위 패키지
  @Pointcut("execution(* hello.springadvanced.aop.order..*(..))")
  private fun allOrder() {} // Pointcut signature

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  private fun allService() {}

  @Around("allOrder()")
  fun doLog(joinPoint: ProceedingJoinPoint): Any? {
    log.info("[log] {}", joinPoint.signature)
    return joinPoint.proceed()
  }

  @Around("allOrder() && allService()")
  fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
    try {
      log.info("[트랜잭션 시작] {}", joinPoint.signature)
      val result = joinPoint.proceed()
      log.info("[트랜잭션 커밋] {}", joinPoint.signature)
      return result
    } catch (e: Exception) {
      log.info("[트랜잭션 롤백] {}", joinPoint.signature)
      throw e
    } finally {
      log.info("[리소스 릴리즈] {}", joinPoint.signature)
    }
  }
}