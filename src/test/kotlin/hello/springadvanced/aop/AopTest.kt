package hello.springadvanced.aop

import hello.springadvanced.aop.order.OrderRepository
import hello.springadvanced.aop.order.OrderService
import hello.springadvanced.aop.order.aop.AspectV6Advice
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(AspectV6Advice::class)
class AopTest {
  private val log: Logger = LoggerFactory.getLogger(AopTest::class.java)

  @Autowired
  private lateinit var orderService: OrderService

  @Autowired
  private lateinit var orderRepository: OrderRepository

  @Test
  fun aopInfo() {
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
    log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
  }

  @Test
  fun success() {
    orderService.orderItem("itemA")
  }

  @Test
  fun exception() {
    assertThatThrownBy { orderService.orderItem("ex") }
      .isInstanceOf(IllegalStateException::class.java)
  }
}