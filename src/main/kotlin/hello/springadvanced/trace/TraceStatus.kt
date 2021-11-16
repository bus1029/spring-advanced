package hello.springadvanced.trace

class TraceStatus constructor(val traceId: TraceId,
                              val startTimeMs: Long,
                              val message: String) {

}