package deconstructinglambdas

import deconstructinglambdas.JSFunc
import deconstructinglambdas.typeclass.{Cartesian, Category, Strong}

def thrice[K[_, _]: Category, A](k: K[A, A]): K[A, A] = 
  k >>> (k >>> k)

def add3: Int => Int = 
  thrice((_: Int) + 1)

def times10: JSFunc[Int, Int] =
  JSFunc("(x => x * 10)")

def times1000: JSFunc[Int, Int] =
  thrice(times10)

def isPalindrome: String => Boolean = 
  // `Category` constraint was not necessary in the original  
  def isPalindromeK[K[_, _]: Cartesian: Strong: Category](
    reverse: K[String, String]
  )(compare: K[(String, String), Boolean]
  ): K[String, Boolean] = 
    Cartesian[K].copy[String] >>> (reverse.first >>> compare)  

  isPalindromeK(reverse = (_: String).reverse)(compare = (ss: (String, String)) => ss._1 == ss._2)

// Did not work =/
/*def isPalindrome[K[_, _]: Category: Cartesian: Strong]: K[String, Boolean] =
  val copy = Cartesian[K].copy[String]
  val reverse = (_: String).reverse
  val compare = (ss: (String, String)) => ss._1 == ss._2
  copy >>> (reverse.first >>> compare)*/