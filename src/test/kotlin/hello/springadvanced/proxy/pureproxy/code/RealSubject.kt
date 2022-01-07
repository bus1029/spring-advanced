package hello.springadvanced.proxy.pureproxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RealSubject : Subject {
  private val log: Logger = LoggerFactory.getLogger(RealSubject::class.java)

  override fun operation(): String {
    log.info("실제 객체 호출")
    sleep(1000)
    return "data"
  }

  private fun sleep(i: Long) {
    Thread.sleep(i)
  }
}