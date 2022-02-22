package hello.springadvanced.proxy.pureproxy.decorator

import hello.springadvanced.proxy.pureproxy.decorator.code.DecoratorPatternClient
import hello.springadvanced.proxy.pureproxy.decorator.code.MessageDecorator
import hello.springadvanced.proxy.pureproxy.decorator.code.RealComponent
import hello.springadvanced.proxy.pureproxy.decorator.code.TimeDecorator
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DecoratorPatternTest {
  private val log: Logger = LoggerFactory.getLogger(DecoratorPatternTest::class.java)

  @Test
  fun noDecorator() {
    val realComponent = RealComponent()
    val decoratorPatternClient = DecoratorPatternClient(realComponent)
    decoratorPatternClient.execute()
  }

  @Test
  fun decorator1() {
    val realComponent = RealComponent()
    val decorator = MessageDecorator(realComponent)
    val client = DecoratorPatternClient(decorator)
    client.execute()
  }

  @Test
  fun decorator2() {
    val realComponent = RealComponent()
    val decorator = MessageDecorator(realComponent)
    val timeDecorator = TimeDecorator(decorator)
    val client = DecoratorPatternClient(timeDecorator)
    client.execute()
  }
}