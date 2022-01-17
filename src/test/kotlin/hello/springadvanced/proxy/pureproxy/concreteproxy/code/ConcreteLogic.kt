package hello.springadvanced.proxy.pureproxy.concreteproxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ConcreteLogic {
  private val log: Logger = LoggerFactory.getLogger(ConcreteLogic::class.java)

  open fun operation(): String {
    log.info("ConcreteLogic 실행")
    return "data"
  }
}