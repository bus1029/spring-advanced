package hello.springadvanced.proxy.jdkdynamic

import hello.springadvanced.proxy.jdkdynamic.code.*
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {
  val log: Logger = LoggerFactory.getLogger(JdkDynamicProxyTest::class.java)

  @Test
  fun dynamicA() {
    val target = AImpl()
    val handler = TimeInvocationHandler(target)
    val proxy = Proxy.newProxyInstance(AInterface::class.java.classLoader, arrayOf(AInterface::class.java), handler)
            as AInterface
    proxy.call()
    log.info("targetClass={}", target.javaClass)
    log.info("proxyClass={}", proxy.javaClass)
  }

  @Test
  fun dynamicB() {
    val target = BImpl()
    val handler = TimeInvocationHandler(target)
    val proxy = Proxy.newProxyInstance(BInterface::class.java.classLoader, arrayOf(BInterface::class.java), handler)
            as BInterface
    proxy.call()
    log.info("targetClass={}", target.javaClass)
    log.info("proxyClass={}", proxy.javaClass)
  }
}