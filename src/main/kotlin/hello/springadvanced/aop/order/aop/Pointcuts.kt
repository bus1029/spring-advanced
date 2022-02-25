package hello.springadvanced.aop.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {
  // hello.springadvanced.aop.order 패키지와 하위 패키지
  @Pointcut("execution(* hello.springadvanced.aop.order..*(..))")
  fun allOrder() {} // Pointcut signature

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  fun allService() {}

  @Pointcut("allOrder() && allService()")
  fun orderAndService() {}
}