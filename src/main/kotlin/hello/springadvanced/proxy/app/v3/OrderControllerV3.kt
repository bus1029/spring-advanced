package hello.springadvanced.proxy.app.v3

import org.springframework.web.bind.annotation.*

@RestController
class OrderControllerV3(
  private val orderService: OrderServiceV3
) {
  @GetMapping("/v3/request")
  fun request(@RequestParam itemId: String): String {
    orderService.orderItem(itemId)
    return "ok"
  }

  @GetMapping("/v3/no-log")
  fun noLog(): String {
    return "ok"
  }
}