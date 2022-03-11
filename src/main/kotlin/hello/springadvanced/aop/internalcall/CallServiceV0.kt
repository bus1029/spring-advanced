package hello.springadvanced.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CallServiceV0 {
  private val log: Logger = LoggerFactory.getLogger(CallServiceV0::class.java)

  fun external() {
    log.info("call external")
    internal()
  }

  fun internal() {
    log.info("call internal")
  }
}