package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
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

  @Test
  fun typeExactMatch() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.MemberServiceImpl.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun typeMatchSuperType() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.MemberService.*(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  fun typeMatchNoSuperTypeMethodFalse() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.MemberService.*(..))"
    val internalMethod = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isFalse
  }

  @Test
  fun typeMatchInternal() {
    pointcut.expression = "execution(* hello.springadvanced.aop.member.MemberServiceImpl.*(..))"
    val internalMethod = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  @DisplayName("String 타입의 파라미터 허용")
  fun argsMatch() {
    pointcut.expression = "execution(* *(String))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  @DisplayName("파라미터가 없어야 함")
  fun argsMatchNoArgs() {
    pointcut.expression = "execution(* *())"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isFalse
  }

  @Test
  @DisplayName("정확히 하나의 파라미터만 허용, 모든 타입 허용")
  fun argsMatchStar() {
    pointcut.expression = "execution(* *(*))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  @DisplayName("숫자와 무관하게 모든 파라미터, 모든 타입 허용")
  fun argsMatchAll() {
    pointcut.expression = "execution(* *(..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  @Test
  @DisplayName("String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용")
  fun argsMatchComplex() {
    pointcut.expression = "execution(* *(String, ..))"
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }
}