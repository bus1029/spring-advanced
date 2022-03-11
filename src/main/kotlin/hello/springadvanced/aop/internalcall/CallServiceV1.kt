package hello.springadvanced.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallServiceV1 {
  private val log: Logger = LoggerFactory.getLogger(CallServiceV1::class.java)
  private var callService: CallServiceV1? = null

  @Autowired
  fun setCallService(callServiceV1: CallServiceV1) {
    log.info("callServiceV1 setter={}", callServiceV1.javaClass)
    this.callService = callServiceV1
  }

  fun external() {
    log.info("call external")
    callService!!.internal()
  }

  fun internal() {
    log.info("call internal")
  }
}