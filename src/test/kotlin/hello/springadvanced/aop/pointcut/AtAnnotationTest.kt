package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.MemberService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(AtAnnotationTest.AtAnnotationAspect::class)
@SpringBootTest
class AtAnnotationTest {
  private val log: Logger = LoggerFactory.getLogger(AtAnnotationTest::class.java)

  @Autowired
  lateinit var memberService: MemberService

  @Test
  fun success() {
    log.info("memberService Proxy={}", memberService.javaClass)
    memberService.hello("helloA")
  }

  @Aspect
  class AtAnnotationAspect {
    private val log: Logger = LoggerFactory.getLogger(AtAnnotationAspect::class.java)

    @Around("@annotation(hello.springadvanced.aop.member.annotation.MethodAop)")
    fun doAtAnnotation(joinPoint: ProceedingJoinPoint): Any? {
      log.info("[@annotation] {}", joinPoint.signature)
      return joinPoint.proceed()
    }
  }

}