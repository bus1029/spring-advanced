package hello.springadvanced.proxy.pureproxy.concreteproxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeProxy constructor(
  private val concreteLogic: ConcreteLogic
) : ConcreteLogic() {
  private val log: Logger = LoggerFactory.getLogger(TimeProxy::class.java)

  override fun operation(): String {
    log.info("TimeDecorator 실행")
    val startTime = System.currentTimeMillis()

    val result = concreteLogic.operation()

    val endTime = System.currentTimeMillis()
    log.info("TimeDecorator 종료, resultTime={}ms", endTime-startTime)
    return result
  }
}