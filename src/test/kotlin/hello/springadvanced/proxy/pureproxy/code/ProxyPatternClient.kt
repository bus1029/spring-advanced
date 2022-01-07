package hello.springadvanced.proxy.pureproxy.code

class ProxyPatternClient (
  private val subject: Subject)
{

  fun execute() {
    subject.operation()
  }
}