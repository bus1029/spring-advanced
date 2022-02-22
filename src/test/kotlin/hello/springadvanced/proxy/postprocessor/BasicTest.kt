package hello.springadvanced.proxy.postprocessor

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest {
  @Test
  fun basicConfig() {
    val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)

    val a = applicationContext.getBean("beanA", A::class.java)
    a.helloA()

    // B 는 스프링 빈으로 등록되지 않는다.
    Assertions.assertThatThrownBy { applicationContext.getBean(B::class.java) }
      .isInstanceOf(NoSuchBeanDefinitionException::class.java)
  }

  @Configuration
  private class BasicConfig {
    private val log: Logger = LoggerFactory.getLogger(BasicConfig::class.java)
    @Bean(name = ["beanA"])
    fun a(): A {
      return A()
    }
  }

  private class A {
    private val log: Logger = LoggerFactory.getLogger(A::class.java)
    fun helloA() {
      log.info("Hello A")
    }
  }

  private class B {
    private val log: Logger = LoggerFactory.getLogger(B::class.java)
    fun helloB() {
      log.info("Hello B")
    }
  }
}