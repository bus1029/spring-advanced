package hello.springadvanced.aop.proxyvs.code

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class ProxyDIAspect {
  private val log: Logger = LoggerFactory.getLogger(this::class.java)

  @Before("execution (* hello.springadvanced.aop..*.*(..))")
  fun doTrace(joinPoint: JoinPoint) {
    log.info("[proxyDIAspect] {}", joinPoint.signature)
  }
}