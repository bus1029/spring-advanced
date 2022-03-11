package hello.springadvanced.aop.exam

import hello.springadvanced.aop.exam.aop.RetryAspect
import hello.springadvanced.aop.exam.aop.TraceAspect
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TraceAspect::class, RetryAspect::class)
@SpringBootTest
class ExamTest {
  private val log: Logger = LoggerFactory.getLogger(ExamTest::class.java)

  @Autowired
  private lateinit var examService: ExamService

  @Test
  fun test() {
    Assertions.assertThatCode {
      for (i in 0..4) {
        log.info("client request i={}", i)
        examService.request("data $i")
      }
    }.doesNotThrowAnyException()
  }
}