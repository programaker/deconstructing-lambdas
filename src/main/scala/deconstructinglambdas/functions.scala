package deconstructinglambdas

import deconstructinglambdas.JSFunc
import deconstructinglambdas.typeclass.{Cartesian, Category, Strong}
import Strong.given
import Category.given
import Cartesian.given

def thrice[K[_, _]: Category, A](k: K[A, A]): K[A, A] = 
  k >>> (k >>> k)

def add3: Int => Int = 
  thrice((_: Int) + 1)

def times10: JSFunc[Int, Int] =
  JSFunc("(x => x * 10)")

def times1000: JSFunc[Int, Int] =
  thrice(times10)

def isPalindrome[K[_, _]: Category: Cartesian: Strong](
  reverse: K[String, String]
)(compare: K[(String, String), Boolean]
): K[String, Boolean] = 
  Cartesian[K].copy[String] >>> (reverse.first >>> compare)  

// Did not work =/
/*def isPalindrome[K[_, _]: Category: Cartesian: Strong]: K[String, Boolean] =
  val copy = Cartesian[K].copy[String]
  val reverse = (_: String).reverse
  val compare = (ss: (String, String)) => ss._1 == ss._2
  copy >>> (reverse.first >>> compare)*/