package hello.springadvanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class ThreadLocalService {
  private val logger = LoggerFactory.getLogger(ThreadLocalService::class.java)
  private var nameStore = ThreadLocal<String>()

  fun logic(name: String): String {
    logger.info("Save name={} -> nameStore={}", name, nameStore.get())
    nameStore.set(name)
    sleep(1000)
    logger.info("Search nameStore={}", nameStore.get())
    return nameStore.get()
  }

  private fun sleep(millis: Int) {
    try {
      Thread.sleep(millis.toLong())
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}