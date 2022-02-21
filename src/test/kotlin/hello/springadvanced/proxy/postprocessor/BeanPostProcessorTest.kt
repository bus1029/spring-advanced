package hello.springadvanced.proxy.postprocessor

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest {
  @Test
  fun basicConfig() {
    val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)

    // beanA 이름으로 B 객체가 빈으로 등록됨
    val a = applicationContext.getBean("beanA", B::class.java)
    a.helloB()

    // A 는 스프링 빈으로 등록되지 않는다.
    Assertions.assertThatThrownBy { applicationContext.getBean(A::class.java) }
      .isInstanceOf(NoSuchBeanDefinitionException::class.java)
  }

  @Configuration
  private class BeanPostProcessorConfig {
    private val log: Logger = LoggerFactory.getLogger(BeanPostProcessorConfig::class.java)
    @Bean(name = ["beanA"])
    fun a(): A {
      return A()
    }

    @Bean
    fun helloPostProcessor(): AToBPostProcessor {
      return AToBPostProcessor()
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

  private class AToBPostProcessor : BeanPostProcessor {
    private val log: Logger = LoggerFactory.getLogger(AToBPostProcessor::class.java)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
      log.info("beanName={}, bean={}", beanName, bean)
      if (bean is A) {
        return B()
      }
      return bean
    }
  }
}