package hello.springadvanced.aop.internalcall

import hello.springadvanced.aop.internalcall.aop.CallLogAspect
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV0Test {
  @Autowired
  private lateinit var callService: CallServiceV0

  @Test
  fun external() {
    callService.external()
  }

  @Test
  fun internal() {
    callService.internal()
  }
}