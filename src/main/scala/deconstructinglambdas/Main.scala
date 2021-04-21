package deconstructinglambdas

@main def run: Unit =
  println(add3(10))
  println("===")

  println(times1000.renderJs)
  println("===")

  // meh =/
  println(isPalindrome("ovo"))
  println(isPalindrome("scala"))
  println("===")

  // yay =D
  val isPalindrome_ = isPalindromeP[Function]
  println(isPalindrome_("evilolive"))
  println(isPalindrome_("turkey"))
  println("===")

  val isPalindromeJs = isPalindromeP[JSFunc]
  println(isPalindromeJs.renderJs)
  println("===")
