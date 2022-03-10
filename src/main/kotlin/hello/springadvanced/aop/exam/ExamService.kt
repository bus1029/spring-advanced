package hello.springadvanced.aop.exam

import hello.springadvanced.aop.exam.annotation.Trace
import org.springframework.stereotype.Service

@Service
class ExamService(
  private val examRepository: ExamRepository
) {
  @Trace
  fun request(itemId: String) {
    examRepository.save(itemId)
  }
}