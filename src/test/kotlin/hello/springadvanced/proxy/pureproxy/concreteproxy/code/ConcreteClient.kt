package hello.springadvanced.proxy.pureproxy.concreteproxy.code

class ConcreteClient constructor(
  private val concreteLogic: ConcreteLogic
) {

  fun execute() {
    concreteLogic.operation()
  }
}