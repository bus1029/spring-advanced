package hello.springadvanced.proxy.advisor

import hello.springadvanced.proxy.common.service.ServiceImpl
import hello.springadvanced.proxy.common.service.ServiceInterface
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest {
  @Test
  @DisplayName("여러 프록시")
  fun multiAdvisorTest1() {
    // client -> proxy2(advisor2) -> proxy1(advisor1) -> target
    val target = ServiceImpl()

    // Create Proxy1
    val proxyFactory1 = ProxyFactory(target)
    val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
    proxyFactory1.addAdvisor(advisor1)
    val proxy1 = proxyFactory1.proxy as ServiceInterface

    // Create Proxy2
    val proxyFactory2 = ProxyFactory(proxy1)
    val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
    proxyFactory2.addAdvisor(advisor2)
    val proxy2 = proxyFactory2.proxy as ServiceInterface

    proxy2.save()
  }

  private class Advice1 : MethodInterceptor {
    private val log: Logger = LoggerFactory.getLogger(Advice1::class.java)
    override fun invoke(invocation: MethodInvocation): Any? {
      log.info("Call advice1")
      return invocation.proceed()
    }
  }

  private class Advice2 : MethodInterceptor {
    private val log: Logger = LoggerFactory.getLogger(Advice2::class.java)
    override fun invoke(invocation: MethodInvocation): Any? {
      log.info("Call advice2")
      return invocation.proceed()
    }
  }

  @Test
  @DisplayName("하나의 프록시, 여러 어드바이저")
  fun multiAdvisorTest2() {
    // client -> proxy(advisor2 -> advisor1) -> target
    val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
    val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())

    val target = ServiceImpl()

    // Create Proxy
    val proxyFactory = ProxyFactory(target)
    proxyFactory.addAdvisor(advisor1)
    proxyFactory.addAdvisor(advisor2)
    val proxy = proxyFactory.proxy as ServiceInterface
    proxy.save()
  }
}