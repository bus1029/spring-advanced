package hello.springadvanced.proxy.config.v1_proxy.interface_proxy

import hello.springadvanced.proxy.app.v1.OrderServiceV1
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderServiceInterfaceProxy constructor(
  private val target: OrderServiceV1,
  private val logTrace: LogTrace
): OrderServiceV1 {
  override fun orderItem(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = logTrace.begin("OrderSerivce.orderItem()")
      target.orderItem(itemId)
      logTrace.end(status)
    } catch (e: Exception) {
      logTrace.exception(status!!, e)
    }
  }
}