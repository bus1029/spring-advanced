package hello.springadvanced.proxy.app.v3

import hello.springadvanced.proxy.app.v2.OrderRepositoryV2
import org.springframework.stereotype.Service

@Service
class OrderServiceV3 (
  private val orderRepository: OrderRepositoryV2
) {
  fun orderItem(itemId: String) {
    orderRepository.save(itemId)
  }
}