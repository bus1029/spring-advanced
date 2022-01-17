package hello.springadvanced.proxy.config.v1_proxy.concrete_proxy

import hello.springadvanced.proxy.app.v2.OrderRepositoryV2
import hello.springadvanced.trace.TraceStatus
import hello.springadvanced.trace.logtrace.LogTrace

class OrderRepositoryConcreteProxy constructor(
  private val target: OrderRepositoryV2,
  private val logTrace: LogTrace
): OrderRepositoryV2() {
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