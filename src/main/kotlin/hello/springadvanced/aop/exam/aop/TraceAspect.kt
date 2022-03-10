package hello.springadvanced.aop.exam.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class TraceAspect {
  private val log: Logger = LoggerFactory.getLogger(TraceAspect::class.java)

  @Before("@annotation(hello.springadvanced.aop.exam.annotation.Trace)")
  fun doTrace(joinPoint: JoinPoint) {
    log.info("[trace] {} args={}", joinPoint.signature, joinPoint.args)
  }
}