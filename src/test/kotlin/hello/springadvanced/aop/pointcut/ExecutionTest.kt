package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
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
    // public java.lang.String hello.springadvanced.aop.member.MemberServiceImpl.hello(java.lang.String)
    log.info("helloMethod={}", helloMethod)
  }

  @Test
  fun exactMatch() {
    // public java.lang.String hello.springadvanced.aop.member.MemberServiceImpl.hello(java.lang.String)
    pointcut.expression = "execution(public String hello.springadvanced.aop.member.MemberServiceImpl.hello(String))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun allMatch() {
    pointcut.expression = "execution(* *(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun nameMatch() {
    pointcut.expression = "execution(* hello(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun nameMatchStar1() {
    pointcut.expression = "execution(* hel*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun nameMatchStar2() {
    pointcut.expression = "execution(* *el*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun nameMatchFalse() {
    pointcut.expression = "execution(* nono(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
  }

  @Test
  fun packageExactMatch() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.MemberServiceImpl.hello(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun packageExactMatch2() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.*.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun packageExactMatchFalse() {
    pointcut.expression = "execution(* hello.springadvanced.aop.*.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
  }

  @Test
  fun packageMatchSubPackage1() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member..*.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun packageMatchSubPackage2() {
    pointcut.expression = "execution(* hello.springadvanced.aop..*.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }
}