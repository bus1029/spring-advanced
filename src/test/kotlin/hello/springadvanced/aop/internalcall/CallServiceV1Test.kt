package hello.springadvanced.aop.internalcall

import hello.springadvanced.aop.internalcall.aop.CallLogAspect
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV1Test {
  @Autowired
  private lateinit var callService: CallServiceV1

  @Test
  fun external() {
    callService.external()
  }
}