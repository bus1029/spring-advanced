package hello.springadvanced.trace.hellotrace

import org.junit.jupiter.api.Test

class HelloTraceV1Test {
  @Test
  fun beginEnd() {
    val helloTraceV1 = HelloTraceV1()
    val status = helloTraceV1.begin("hello")
    helloTraceV1.end(status)
  }

  @Test
  fun beginException() {
    val helloTraceV1 = HelloTraceV1()
    val status = helloTraceV1.begin("hello")
    helloTraceV1.exception(status, IllegalStateException())
  }
}