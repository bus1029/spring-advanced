package hello.springadvanced.proxy.config.v1_proxy.interface_proxy

import hello.springadvanced.proxy.app.v1.OrderRepositoryV1
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderRepositoryInterfaceProxy constructor(
  private val target: OrderRepositoryV1,
  private val logTrace: LogTrace
) : OrderRepositoryV1 {
  override fun save(itemId: String) {
    var status: TraceStatus? = null
    try {
      status = logTrace.begin("OrderRepository.request()")
      target.save(itemId)
      logTrace.end(status)
    } catch (e: Exception) {
      logTrace.exception(status!!, e)
    }
  }
}