package hello.springadvanced.aop.member.annotation

import hello.springadvanced.aop.member.MemberService
import org.springframework.stereotype.Component

@ClassAop
@Component
class MemberServiceImpl : MemberService {
  @MethodAop("test value")
  override fun hello(param: String): String {
    return "ok"
  }

  fun internal(param: String): String {
    return "ok"
  }
}