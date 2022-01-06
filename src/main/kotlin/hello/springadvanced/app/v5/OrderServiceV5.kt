package hello.springadvanced.app.v5

import hello.springadvanced.trace.callback.TraceCallback
import hello.springadvanced.trace.callback.TraceTemplate
import hello.springadvanced.trace.logtrace.LogTrace
import hello.springadvanced.trace.template.AbstractTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceV5 constructor(
  private val orderRepository: OrderRepositoryV5,
  )
{
  private lateinit var traceTemplate: TraceTemplate

  @Autowired
  constructor(orderRepository: OrderRepositoryV5, trace: LogTrace) : this(orderRepository) {
    this.traceTemplate = TraceTemplate(trace)
  }

  fun orderItem(itemId: String) {
    return traceTemplate.execute("OrderService.orderItem()", object: TraceCallback<Unit> {
      override fun call() {
        orderRepository.save(itemId)
        return
      }
    })
  }
}