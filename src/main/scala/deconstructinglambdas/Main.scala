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