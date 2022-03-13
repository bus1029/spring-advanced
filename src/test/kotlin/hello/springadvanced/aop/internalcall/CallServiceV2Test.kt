package hello.springadvanced.aop.internalcall

import hello.springadvanced.aop.internalcall.aop.CallLogAspect
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
class CallServiceV2Test {
  private val log: Logger = LoggerFactory.getLogger(CallServiceV2Test::class.java)

  @Autowired
  private lateinit var callService: CallServiceV2

  @Test
  fun external() {
    callService.external()
  }
}