package hello.springadvanced.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class InternalService {
  private val log: Logger = LoggerFactory.getLogger(this::class.java)

  fun internal() {
    log.info("call internal")
  }
}