package hello.springadvanced.aop.internalcall

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class CallServiceV2(
  private val applicationContext: ApplicationContext,
  private val objectProvider: ObjectProvider<CallServiceV2>
) {
  private val log: Logger = LoggerFactory.getLogger(CallServiceV2::class.java)

  fun external() {
    log.info("call external")
//    val callService = applicationContext.getBean(CallServiceV2::class.java)
    val callService = objectProvider.getObject()
    callService.internal()
  }

  fun internal() {
    log.info("call internal")
  }
}