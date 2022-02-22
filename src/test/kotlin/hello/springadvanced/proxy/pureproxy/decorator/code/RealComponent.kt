package hello.springadvanced.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RealComponent : Component {
  private val log: Logger = LoggerFactory.getLogger(RealComponent::class.java)

  override fun operation(): String {
    log.info("RealComponent 실행")
    return "data"
  }
}