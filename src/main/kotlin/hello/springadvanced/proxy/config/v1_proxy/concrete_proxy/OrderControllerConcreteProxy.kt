package hello.springadvanced.proxy.config.v1_proxy.concrete_proxy

import hello.springadvanced.proxy.app.v2.OrderControllerV2
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderControllerConcreteProxy constructor(
  private val target: OrderControllerV2,
  private val logTrace: LogTrace
) : OrderControllerV2(null) {
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