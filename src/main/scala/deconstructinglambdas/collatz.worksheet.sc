import deconstructinglambdas.functions.collatzStep
import deconstructinglambdas.JSFunc

val collatzStepFn = collatzStep[Function]
collatzStepFn(3)
collatzStepFn(4)
collatzStepFn(5)

val collatzStepJs = collatzStep[JSFunc]
collatzStepJs.renderJs
