import deconstructinglambdas.JSFunc
import deconstructinglambdas.functions.thrice

val times10: JSFunc[Int, Int] = JSFunc("(x => x * 10)")
val times1000: JSFunc[Int, Int] = thrice(times10)

times1000.renderJs
