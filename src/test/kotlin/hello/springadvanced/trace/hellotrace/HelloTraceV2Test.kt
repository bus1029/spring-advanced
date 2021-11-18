package hello.springadvanced.trace.hellotrace

import org.junit.jupiter.api.Test

class HelloTraceV2Test {
  @Test
  fun beginEnd() {
    val helloTraceV2 = HelloTraceV2()
    val status = helloTraceV2.begin("hello")
    val status2 = helloTraceV2.beginSync(status.traceId, "hello2")
    val status3 = helloTraceV2.beginSync(status2.traceId, "hello3")
    helloTraceV2.end(status3)
    helloTraceV2.end(status2)
    helloTraceV2.end(status)
  }

  @Test
  fun beginException() {
    val helloTraceV2 = HelloTraceV2()
    val status = helloTraceV2.begin("hello")
    val status2 = helloTraceV2.beginSync(status.traceId, "hello2")
    val status3 = helloTraceV2.beginSync(status2.traceId, "hello3")
    helloTraceV2.exception(status3, IllegalStateException())
    helloTraceV2.exception(status2, IllegalStateException())
    helloTraceV2.exception(status, IllegalStateException())
  }
}