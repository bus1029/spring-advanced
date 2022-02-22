package hello.springadvanced.proxy.config.v4_postprocessor

import hello.springadvanced.proxy.config.AppV1Config
import hello.springadvanced.proxy.config.AppV2Config
import hello.springadvanced.proxy.config.v3_proxyfactory.advice.LogTraceAdvice
import hello.springadvanced.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor
import hello.springadvanced.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(AppV1Config::class, AppV2Config::class)
class BeanPostProcessorConfig {
  private val log: Logger = LoggerFactory.getLogger(BeanPostProcessorConfig::class.java)

  @Bean
  fun logTracePostProcessor(logTrace: LogTrace): BeanPostProcessor {
    return PackageLogTracePostProcessor("hello.springadvanced.proxy.app", getAdvisor(logTrace))
  }

  private fun getAdvisor(logTrace: LogTrace): Advisor {
    val pointcut = NameMatchMethodPointcut()
    pointcut.setMappedNames("request*", "order*", "save*")
    val advice = LogTraceAdvice(logTrace)
    return DefaultPointcutAdvisor(pointcut, advice)
  }
}