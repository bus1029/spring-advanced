package hello.springadvanced

import hello.springadvanced.proxy.config.AppV1Config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(AppV1Config::class)
@SpringBootApplication(scanBasePackages = ["hello.springadvanced.proxy"]) // 주의
class SpringAdvancedApplication

fun main(args: Array<String>) {
	runApplication<SpringAdvancedApplication>(*args)
}
