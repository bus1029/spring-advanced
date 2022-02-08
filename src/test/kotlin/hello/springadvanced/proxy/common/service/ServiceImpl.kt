package hello.springadvanced.proxy.common.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ServiceImpl : ServiceInterface {
  val log: Logger = LoggerFactory.getLogger(ServiceImpl::class.java)

  override fun save() {
    log.info("Call save")
  }

  override fun find() {
    log.info("Call find")
  }
}