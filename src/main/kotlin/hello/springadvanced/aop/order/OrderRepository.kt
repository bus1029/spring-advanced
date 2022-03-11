package hello.springadvanced.aop.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
  private val log: Logger = LoggerFactory.getLogger(OrderRepository::class.java)

  fun save(itemId: String) {
    log.info("[orderRepository] 실행")
    if (itemId == "ex") {
      throw IllegalStateException("예외 발생!")
    }
  }
}