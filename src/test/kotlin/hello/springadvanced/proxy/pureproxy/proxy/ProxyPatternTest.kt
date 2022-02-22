package hello.springadvanced.proxy.pureproxy.proxy

import hello.springadvanced.proxy.pureproxy.proxy.code.CacheProxy
import hello.springadvanced.proxy.pureproxy.proxy.code.ProxyPatternClient
import hello.springadvanced.proxy.pureproxy.proxy.code.RealSubject
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ProxyPatternTest {
  private val log: Logger = LoggerFactory.getLogger(ProxyPatternTest::class.java)

  @Test
  fun noProxyTest() {
    val realSubject = RealSubject()
    val proxyPatternClient = ProxyPatternClient(realSubject)
    val startTime = System.currentTimeMillis()
    proxyPatternClient.execute()
    proxyPatternClient.execute()
    proxyPatternClient.execute()
    val endTime = System.currentTimeMillis()
    log.info("Duration: {}ms", endTime-startTime)
  }

  @Test
  fun cacheProxyTest() {
    val realSubject = RealSubject()
    val cacheProxy = CacheProxy(realSubject)
    val proxyPatternClient = ProxyPatternClient(cacheProxy)
    val startTime = System.currentTimeMillis()
    proxyPatternClient.execute()
    proxyPatternClient.execute()
    proxyPatternClient.execute()
    val endTime = System.currentTimeMillis()
    log.info("Duration: {}ms", endTime-startTime)
  }
}