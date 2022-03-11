package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.MemberService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

/**
 * application.properties
 *  spring.aop.proxy-target-class=true CGLIB 만 사용 (기본값)
 *  spring.aop.proxy-target-class=false JDK 동적 프록시도 사용
 */
@Import(ThisTargetTest.ThisTargetAspect::class)
@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
class ThisTargetTest {
  private val log: Logger = LoggerFactory.getLogger(ThisTargetTest::class.java)

  @Autowired
  private lateinit var memberService: MemberService

  @Test
  fun success() {
    log.info("memberService Proxy={}", memberService.javaClass)
    memberService.hello("helloA")
  }

  @Aspect
  class ThisTargetAspect {
    private val log: Logger = LoggerFactory.getLogger(ThisTargetAspect::class.java)

    @Before("this(hello.springadvanced.aop.member.MemberService)")
    fun doThisInterface(joinPoint: JoinPoint) {
      log.info("[this-interface] {}", joinPoint.signature)
    }

    @Before("target(hello.springadvanced.aop.member.MemberService)")
    fun doTargetInterface(joinPoint: JoinPoint) {
      log.info("[target-interface] {}", joinPoint.signature)
    }

    @Before("this(hello.springadvanced.aop.member.MemberServiceImpl)")
    fun doThis(joinPoint: JoinPoint) {
      log.info("[this-impl] {}", joinPoint.signature)
    }

    @Before("target(hello.springadvanced.aop.member.MemberServiceImpl)")
    fun doTarget(joinPoint: JoinPoint) {
      log.info("[target-impl] {}", joinPoint.signature)
    }
  }
}