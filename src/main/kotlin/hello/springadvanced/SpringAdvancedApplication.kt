package hello.springadvanced

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringAdvancedApplication

fun main(args: Array<String>) {
	runApplication<SpringAdvancedApplication>(*args)
}
