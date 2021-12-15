package hello.springadvanced

import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.logtrace.ThreadLocalLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
  @Bean
  fun logTrace(): LogTrace {
    return ThreadLocalLogTrace()
  }
}