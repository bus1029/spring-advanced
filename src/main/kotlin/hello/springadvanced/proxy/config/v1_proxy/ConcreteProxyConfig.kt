package hello.springadvanced.proxy.config.v1_proxy

import hello.springadvanced.proxy.app.v2.OrderControllerV2
import hello.springadvanced.proxy.app.v2.OrderRepositoryV2
import hello.springadvanced.proxy.app.v2.OrderServiceV2
import hello.springadvanced.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy
import hello.springadvanced.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy
import hello.springadvanced.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy
import hello.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConcreteProxyConfig {
  @Bean
  fun orderControllerV2(logTrace: LogTrace): OrderControllerV2 {
    val orderControllerV2 = OrderControllerV2(orderServiceV2(logTrace))
    return OrderControllerConcreteProxy(orderControllerV2, logTrace)
  }

  @Bean
  fun orderServiceV2(logTrace: LogTrace): OrderServiceV2 {
    val orderServiceV2 = OrderServiceV2(orderRepositoryV2(logTrace))
    return OrderServiceConcreteProxy(orderServiceV2, logTrace)
  }

  @Bean
  fun orderRepositoryV2(logTrace: LogTrace): OrderRepositoryV2 {
    val orderRepositoryV2 = OrderRepositoryV2()
    return OrderRepositoryConcreteProxy(orderRepositoryV2, logTrace)
  }
}