package deconstructinglambdas

@main def run: Unit =
  println(add3(10))
  println("===")

  println(times1000.renderJs)
  println("===")

  // meh =/
  val palindrome = isPalindrome(reverse = (_: String).reverse)(compare = (ss: (String, String)) => ss._1 == ss._2)
  println(palindrome("ovo"))
  println(palindrome("scala"))
  println("===")