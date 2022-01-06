package hello.springadvanced.app.v5

import hello.springadvanced.trace.callback.TraceCallback
import hello.springadvanced.trace.callback.TraceTemplate
import hello.springadvanced.trace.logtrace.LogTrace
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV5() {
  val log: Logger = LoggerFactory.getLogger(OrderRepositoryV5::class.java)
  private lateinit var traceTemplate: TraceTemplate

  @Autowired
  constructor(trace: LogTrace) : this() {
    this.traceTemplate = TraceTemplate(trace)
  }

  fun save(itemId: String) {
    return traceTemplate.execute("OrderRepository.save()", object: TraceCallback<Unit> {
      override fun call() {
        // Save logic
        if ("ex" == itemId) {
          throw IllegalArgumentException("Exception occurred!")
        }
        sleep(1000)
      }
    })
  }

  fun sleep(milliSecond: Int) {
    try {
      Thread.sleep(milliSecond.toLong())
    } catch (e: InterruptedException) {
      log.error(e.toString())
    }
  }
}