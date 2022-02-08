package hello.springadvanced.proxy.common.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ConcreteService {
  val log: Logger = LoggerFactory.getLogger(ConcreteService::class.java)

  fun call() {
    log.info("Call ConcreteService")
  }
}