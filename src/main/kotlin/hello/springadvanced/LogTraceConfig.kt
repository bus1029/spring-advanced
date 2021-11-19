package hello.springadvanced

import hello.springadvanced.trace.logtrace.FieldLogTrace
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LogTraceConfig {
  @Bean
  fun logTrace(): FieldLogTrace {
    return FieldLogTrace()
  }
}