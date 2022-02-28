package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.annotation.MemberServiceImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ExecutionTest {
  private val log: Logger = LoggerFactory.getLogger(ExecutionTest::class.java)
  private val pointcut = AspectJExpressionPointcut()
  private lateinit var helloMethod: Method

  @BeforeEach
  fun init() {
    helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
  }

  @Test
  fun printMethod() {
    // public java.lang.String hello.springadvanced.aop.member.annotation.MemberServiceImpl.hello(java.lang.String)
    log.info("helloMethod={}", helloMethod)
  }
}