package hello.springadvanced.proxy.config.v1_proxy.interface_proxy

import hello.springadvanced.proxy.app.v1.OrderControllerV1
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderControllerInterfaceProxy constructor(
  private val target: OrderControllerV1,
  private val logTrace: LogTrace
): OrderControllerV1 {
  override fun request(itemId: String): String {
    var status: TraceStatus? = null
    try {
      status = logTrace.begin("OrderController.request()")
      val result = target.request(itemId)
      logTrace.end(status)
      return result
    } catch (e: Exception) {
      logTrace.exception(status!!, e)
      throw e
    }
  }

  override fun noLog(): String {
    return target.noLog()
  }
}