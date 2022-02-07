package hello.springadvanced

import hello.springadvanced.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig
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
@Import(DynamicProxyFilterConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.springadvanced.proxy.app"]) // 주의
class SpringAdvancedApplication {
  @Bean
  fun logTrace(): LogTrace {
    return ThreadLocalLogTrace()
  }
}

fun main(args: Array<String>) {
  runApplication<SpringAdvancedApplication>(*args)
}
