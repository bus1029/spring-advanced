package hello.springadvanced.proxy.app.v2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@RequestMapping("/v2")
@ResponseBody
class OrderControllerV2(
  private val orderService: OrderServiceV2
) {
  @GetMapping("/request")
  fun request(@RequestParam itemId: String): String {
    orderService.orderItem(itemId)
    return "ok"
  }

  @GetMapping("/no-log")
  fun noLog(): String {
    return "ok"
  }
}