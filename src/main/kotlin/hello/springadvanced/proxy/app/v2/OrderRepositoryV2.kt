package hello.springadvanced.proxy.app.v2

open class OrderRepositoryV2 {
  open fun save(itemId: String) {
    if (itemId == "ex") {
      throw IllegalStateException("예외 발생!")
    }
    sleep(1000)
  }

  private fun sleep(millis: Long) {
    Thread.sleep(millis)
  }
}