package hello.springadvanced.trace

import java.util.*

class TraceId private constructor(var id: String, var level: Int) {
  constructor() : this("", 0) {
    id = createId()
    level = 0
  }

  private fun createId(): String {
    return UUID.randomUUID().toString().substring(0, 8)
  }

  fun createNextId(): TraceId {
    return TraceId(id, level+1)
  }

  fun createPreviousId(): TraceId {
    return TraceId(id, level-1)
  }

  fun isFirstLevel(): Boolean {
    return level == 0
  }
}
