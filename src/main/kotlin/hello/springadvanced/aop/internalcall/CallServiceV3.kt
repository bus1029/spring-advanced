package hello.springadvanced.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * 구조를 변경 (분리)
 */
@Component
class CallServiceV3(
  private val internalService: InternalService
) {
  private val log: Logger = LoggerFactory.getLogger(this::class.java)

  fun external() {
    log.info("call external")
    internalService.internal()
  }
}