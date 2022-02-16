package hello.springadvanced.proxy.config.v3_proxyfactory

import hello.springadvanced.proxy.app.v2.OrderControllerV2
import hello.springadvanced.proxy.app.v2.OrderRepositoryV2
import hello.springadvanced.proxy.app.v2.OrderServiceV2
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
class ProxyFactoryConfigV2 {
  private val log: Logger = LoggerFactory.getLogger(ProxyFactoryConfigV2::class.java)

  @Bean
  fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
    val controller = OrderControllerV2(orderServiceV2(logTrace))

    val factory = ProxyFactory(controller)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderControllerV2
    log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, controller.javaClass)
    return proxy
  }

  @Bean
  fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
    val service = OrderServiceV2(orderRepositoryV2(logTrace))

    val factory = ProxyFactory(service)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderServiceV2
    log.info("ProxyFactory proxy={}, target={}", proxy.javaClass, service.javaClass)
    return proxy
  }

  @Bean
  fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
    val repository = OrderRepositoryV2()

    val factory = ProxyFactory(repository)
    factory.addAdvisor(getAdvisor(logTrace))
    val proxy = factory.proxy as OrderRepositoryV2
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