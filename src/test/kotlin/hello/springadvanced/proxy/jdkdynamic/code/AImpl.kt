package hello.springadvanced.proxy.jdkdynamic.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AImpl : AInterface {
  val log: Logger = LoggerFactory.getLogger(AImpl::class.java)

  override fun call(): String {
    log.info("Call A")
    return "a"
  }
}