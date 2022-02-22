package hello.springadvanced.proxy.config.v4_postprocessor.postprocessor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTracePostProcessor(
  private val basePackage: String,
  private val advisor: Advisor
) : BeanPostProcessor {
  private val log: Logger = LoggerFactory.getLogger(PackageLogTracePostProcessor::class.java)

  override fun postProcessAfterInitialization(bean: Any, beanName: String): Any? {
    log.info("param beanName={}, bean={}", beanName, bean.javaClass)
    if (isNotStartsWithBasePackage(bean)) {
      return bean
    }

    return createProxy(bean)
  }

  private fun isNotStartsWithBasePackage(bean: Any): Boolean {
    val packageName = bean.javaClass.packageName
    return !packageName.startsWith(basePackage)
  }

  private fun createProxy(bean: Any): Any {
    val proxyFactory = ProxyFactory(bean)
    proxyFactory.addAdvisor(advisor)
    val proxy = proxyFactory.proxy
    log.info("create proxy: target={}, proxy={}", bean.javaClass, proxy.javaClass)
    return proxy
  }
}