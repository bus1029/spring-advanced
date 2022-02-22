package hello.springadvanced.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MessageDecorator(component: Component) : Decorator(component), Component {
  private val logger: Logger = LoggerFactory.getLogger(MessageDecorator::class.java)

  override fun operation(): String {
    logger.info("MessageDecorator 실행")
    val operation = component.operation()
    val decoOperation = "*****$operation*****"
    logger.info("MessageDecorator 꾸미기 적용")
    return decoOperation
  }
}