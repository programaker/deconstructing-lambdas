package deconstructinglambdas

@main def run: Unit =
  println(add3(10))
  println("===")

  println(times1000.renderJs)
  println("===")

  val isPalindromeFn = isPalindrome[Function]
  println(isPalindromeFn("evilolive"))
  println(isPalindromeFn("turkey"))
  println("===")

  val isPalindromeJs = isPalindrome[JSFunc]
  println(isPalindromeJs.renderJs)
  println("===")

  val isEvenFn = isEven[Function]
  println(isEvenFn(2))
  println(isEvenFn(3))
  println("===")
