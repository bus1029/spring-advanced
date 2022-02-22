package hello.springadvanced.proxy.pureproxy.concreteproxy

import hello.springadvanced.proxy.pureproxy.concreteproxy.code.ConcreteClient
import hello.springadvanced.proxy.pureproxy.concreteproxy.code.ConcreteLogic
import hello.springadvanced.proxy.pureproxy.concreteproxy.code.TimeProxy
import org.junit.jupiter.api.Test

class ConcreteProxyTest {
  @Test
  fun noProxy() {
    val concreteLogic = ConcreteLogic()
    val concreteClient = ConcreteClient(concreteLogic)
    concreteClient.execute()
  }

  @Test
  fun addProxy() {
    val concreteLogic = ConcreteLogic()
    val timeProxy = TimeProxy(concreteLogic)
    val concreteClient = ConcreteClient(timeProxy)
    concreteClient.execute()
  }
}