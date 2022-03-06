package hello.springadvanced.aop.pointcut

import hello.springadvanced.aop.member.MemberServiceImpl
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.reflect.Method

class ArgsTest {
  private lateinit var helloMethod: Method

  @BeforeEach
  fun init() {
    helloMethod = MemberServiceImpl::class.java.getMethod("hello", String::class.java)
  }

  @Test
  fun args() {
    Assertions.assertThat(pointcut("args(String)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args(Object)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args()")
      .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    Assertions.assertThat(pointcut("args(..)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args(*)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args(String,..)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
  }

  /**
   * execution(* *(java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
   * args(java.io.Serializable): 런타임에 전달된 인수로 판단 (동적)
   */
  @Test
  fun argsVsExecution() {
    // Args
    Assertions.assertThat(pointcut("args(String)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args(java.io.Serializable)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("args(Object)")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue

    // Execution
    Assertions.assertThat(pointcut("execution(* *(String))")
      .matches(helloMethod, MemberServiceImpl::class.java)).isTrue
    Assertions.assertThat(pointcut("execution(* *(java.io.Serializable))")
      .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
    Assertions.assertThat(pointcut("execution(* *(Object))")
      .matches(helloMethod, MemberServiceImpl::class.java)).isFalse
  }

  private fun pointcut(expression: String): AspectJExpressionPointcut {
    val pointcut = AspectJExpressionPointcut()
    pointcut.expression = expression
    return pointcut
  }
}