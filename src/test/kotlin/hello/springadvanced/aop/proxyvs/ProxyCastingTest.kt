package hello.springadvanced.aop.proxyvs

import hello.springadvanced.aop.member.MemberService
import hello.springadvanced.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.ProxyFactory
import java.lang.ClassCastException


class ProxyCastingTest {
  private val log: Logger = LoggerFactory.getLogger(this::class.java)

  @Test
  fun jdkProxy() {
    val target = MemberServiceImpl()
    val proxyFactory = ProxyFactory(target)
    proxyFactory.isProxyTargetClass = false // JDK 동적 프록시

    // 프록시를 인터페이스로 캐스팅
    val memberServiceProxy = proxyFactory.proxy as MemberService
    assertThatThrownBy {
      memberServiceProxy as MemberServiceImpl
    }.isInstanceOf(ClassCastException::class.java)
  }

  @Test
  fun cglibProxy() {
    val target = MemberServiceImpl()
    val proxyFactory = ProxyFactory(target)
    proxyFactory.isProxyTargetClass = true // CGLIB 동적 프록시
    val memberServiceProxy = proxyFactory.proxy as MemberService
    assertDoesNotThrow {
      memberServiceProxy as MemberServiceImpl
    }
  }
}