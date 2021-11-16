package hello.springadvanced.app.v0

import org.springframework.stereotype.Service

@Service
class OrderServiceV0 constructor(private val orderRepository: OrderRepositoryV0) {
  fun orderItem(itemId: String) {
    orderRepository.save(itemId)
  }
}