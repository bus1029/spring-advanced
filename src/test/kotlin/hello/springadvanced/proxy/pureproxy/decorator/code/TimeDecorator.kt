package hello.springadvanced.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TimeDecorator (component: Component) : Decorator(component), Component {
  private val logger: Logger = LoggerFactory.getLogger(TimeDecorator::class.java)

  override fun operation(): String {
    logger.info("TimeDecorator 실행")
    val startTime = System.currentTimeMillis()
    val result = component.operation()
    val endTime = System.currentTimeMillis()
    logger.info("TimeDecorator 종료, resultTime={}ms", endTime-startTime)
    return result
  }
}