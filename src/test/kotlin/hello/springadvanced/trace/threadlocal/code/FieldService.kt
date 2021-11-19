package hello.springadvanced.trace.threadlocal.code

import org.slf4j.LoggerFactory

class FieldService {
  private val logger = LoggerFactory.getLogger(FieldService::class.java)
  private var nameStore: String = ""

  fun logic(name: String): String {
    logger.info("Save name={} -> nameStore={}", name, nameStore)
    nameStore = name
    sleep(1000)
    logger.info("Search nameStore={}", nameStore)
    return nameStore
  }

  private fun sleep(millis: Int) {
    try {
      Thread.sleep(millis.toLong())
    } catch (e: InterruptedException) {
      logger.error(e.toString())
    }
  }
}