package hello.springadvanced.proxy.config.v1_proxy.concrete_proxy

import hello.springadvanced.proxy.app.v2.OrderServiceV2
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderServiceConcreteProxy constructor(
  private val target: OrderServiceV2,
  private val logTrace: LogTrace,
): OrderServiceV2(null) {
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