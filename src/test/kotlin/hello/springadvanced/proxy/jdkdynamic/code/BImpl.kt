package hello.springadvanced.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class BImpl : BInterface {
  val log: Logger = LoggerFactory.getLogger(BImpl::class.java)

  override fun call(): String {
    log.info("Call B")
    return "b"
  }
}