package hello.springadvanced.aop.exam

import hello.springadvanced.aop.exam.annotation.Retry
import hello.springadvanced.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {
  companion object {
    private var seq: Int = 0
  }

  /**
   * 5번에 1번 실패하는 요청
   */
  @Trace
  @Retry(value = 4)
  fun save(itemId: String): String {
    if (++seq % 5 == 0) {
      throw IllegalStateException("Exception Occurred")
    }
    return "ok"
  }
}