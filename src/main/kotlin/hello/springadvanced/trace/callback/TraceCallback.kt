package hello.springadvanced.trace.callback

interface TraceCallback<T> {
  fun call(): T
}