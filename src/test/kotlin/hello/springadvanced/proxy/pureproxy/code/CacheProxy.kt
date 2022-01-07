package hello.springadvanced.proxy.pureproxy.code

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class CacheProxy (
  private val target: Subject)
  : Subject
{
  private val log: Logger = LoggerFactory.getLogger(CacheProxy::class.java)
  private var cacheValue: String? = null

  override fun operation(): String {
    log.info("프록시 호출")
    if (cacheValue == null) {
      cacheValue = target.operation()
    }
    return cacheValue!!
  }
}