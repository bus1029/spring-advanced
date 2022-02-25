package hello.springadvanced.aop.order.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order

@Aspect
class AspectV6Advice {
  private val log: Logger = LoggerFactory.getLogger(AspectV6Advice::class.java)

  @Around("hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()")
  fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
    try {
      // @Before
      log.info("[트랜잭션 시작] {}", joinPoint.signature)
      val result = joinPoint.proceed()
      // @AfterReturning
      log.info("[트랜잭션 커밋] {}", joinPoint.signature)
      return result
    } catch (e: Exception) {
      // @AfterThrowing
      log.info("[트랜잭션 롤백] {}", joinPoint.signature)
      throw e
    } finally {
      // @After
      log.info("[리소스 릴리즈] {}", joinPoint.signature)
    }
  }

  @Before("hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()")
  fun doBefore(joinPoint: JoinPoint) {
    log.info("[before] {}", joinPoint.signature)
  }

  @AfterReturning(value = "hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
  fun doReturn(joinPoint: JoinPoint, result: Any?) {
    log.info("[return] {} return={}", joinPoint.signature, result)
  }

  @AfterThrowing(value = "hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
  fun doThrowing(joinPoint: JoinPoint, ex: Exception) {
    log.info("[ex] {} message={}", joinPoint.signature, ex)
  }

  @After("hello.springadvanced.aop.order.aop.Pointcuts.orderAndService()")
  fun doAfter(joinPoint: JoinPoint) {
    log.info("[after] {}", joinPoint.signature)
  }
}