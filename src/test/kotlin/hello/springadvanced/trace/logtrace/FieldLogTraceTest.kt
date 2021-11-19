package hello.springadvanced.trace.logtrace

import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class FieldLogTraceTest {
  private val trace = FieldLogTrace()

  @Test
  fun beginEndLevel2() {
    val status1 = trace.begin("hello1")
    val status2 = trace.begin("hello2")
    trace.end(status2)
    trace.end(status1)
  }

  @Test
  fun beginExceptionLevel2() {
    val status1 = trace.begin("hello1")
    val status2 = trace.begin("hello2")
    trace.exception(status2, IllegalStateException())
    trace.exception(status1, IllegalStateException())
  }
}