package hello.springadvanced

import hello.springadvanced.proxy.config.v6_aop.AopConfig
import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
//@Import(InterfaceProxyConfig::class)
//@Import(ConcreteProxyConfig::class)
//@Import(DynamicProxyBasicConfig::class)
//@Import(DynamicProxyFilterConfig::class)
//@Import(ProxyFactoryConfigV1::class)
//@Import(ProxyFactoryConfigV2::class)
//@Import(BeanPostProcessorConfig::class)
//@Import(AutoProxyConfig::class)
@Import(AopConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.springadvanced.aop"]) // 주의
class SpringAdvancedApplication {
  @Bean
  fun logTrace(): LogTrace {
    return ThreadLocalLogTrace()
  }
}

fun main(args: Array<String>) {
  runApplication<SpringAdvancedApplication>(*args)
}
