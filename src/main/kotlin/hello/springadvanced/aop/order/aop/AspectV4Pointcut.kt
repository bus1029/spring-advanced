package hello.springadvanced.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV4Pointcut {
  private val log: Logger = LoggerFactory.getLogger(AspectV4Pointcut::class.java)

  @Around("hello.springadvanced.aop.order.aop.Pointcuts.allOrder()")
  fun doLog(joinPoint: ProceedingJoinPoint): Any? {
    log.info("[log] {}", joinPoint.signature)
    return joinPoint.proceed()
  }

  @Around("hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()")
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