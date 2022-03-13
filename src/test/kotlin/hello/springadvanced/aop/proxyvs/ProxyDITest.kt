package hello.springadvanced.aop.proxyvs

import hello.springadvanced.aop.member.MemberService
import hello.springadvanced.aop.member.MemberServiceImpl
import hello.springadvanced.aop.proxyvs.code.ProxyDIAspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ProxyDIAspect::class)
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"])
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"])
class ProxyDITest {
  private val log: Logger = LoggerFactory.getLogger(this::class.java)

  @Autowired
  private lateinit var memberService: MemberService // JDK 동적 프록시 OK, CGLIB OK
  @Autowired
  private lateinit var memberServiceImpl: MemberServiceImpl // JDK 동적 프록시 X, CGLIB OK

  @Test
  fun go() {
    log.info("memberService class={}", memberService.javaClass)
    log.info("memberServiceImpl class={}", memberServiceImpl.javaClass)
    memberServiceImpl.hello("hello")
  }
}