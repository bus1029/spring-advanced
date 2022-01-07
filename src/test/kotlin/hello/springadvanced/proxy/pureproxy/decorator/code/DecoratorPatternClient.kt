package hello.springadvanced.proxy.pureproxy.decorator.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DecoratorPatternClient (
  private val component: Component)
{
  private val log: Logger = LoggerFactory.getLogger(DecoratorPatternClient::class.java)

  fun execute() {
    val result = component.operation()
    log.info("result: {}", result)
  }
}