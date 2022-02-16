package hello.springadvanced.proxy.config.v3_proxyfactory

import hello.springadvanced.proxy.app.v1.*
import hello.springadvanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.springadvanced.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProxyFactoryConfigV1 {
  private val log: Logger = LoggerFactory.getLogger(ProxyFactoryConfigV1::class.java)

  @Bean
  fun orderControllerV1(logTrace: LogTrace): OrderControllerV1 {
    val controller = OrderControllerV1Impl(orderServiceV1(logTrace))

    val factory = ProxyFactory(controller)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderControllerV1
    log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, controller.javaClass)
    return proxy
  }

  @Bean
  fun orderServiceV1(logTrace: LogTrace): OrderServiceV1 {
    val service = OrderServiceV1Impl(orderRepositoryV1(logTrace))

    val factory = ProxyFactory(service)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderServiceV1
    log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, service.javaClass)
    return proxy
  }

  @Bean
  fun orderRepositoryV1(logTrace: LogTrace): OrderRepositoryV1 {
    val repository = OrderRepositoryV1Impl()

    val factory = ProxyFactory(repository)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderRepositoryV1
    log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, repository.javaClass)
    return proxy
  }

  private fun getAdvisor(logTrace: LogTrace): Advisor {
    val pointcut = NameMatchMethodPointcut()
    pointcut.setMappedNames("request*", "order*", "save*")
    val advice = LogTraceAdvice(logTrace)
    return DefaultPointcutAdvisor(pointcut, advice)
  }
}