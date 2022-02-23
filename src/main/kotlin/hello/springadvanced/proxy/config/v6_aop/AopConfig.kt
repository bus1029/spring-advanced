package hello.springadvanced.proxy.config.v6_aop

import hello.springadvanced.proxy.config.AppV1Config
import hello.springadvanced.proxy.config.AppV2Config
import hello.springadvanced.proxy.config.v6_aop.aspect.LogTraceAspect
import hello.springadvanced.trace.logtrace.LogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class AopConfig {
  @Bean
  fun logTraceAspect(logTrace: LogTrace): LogTraceAspect {
    return LogTraceAspect(logTrace)
  }
}