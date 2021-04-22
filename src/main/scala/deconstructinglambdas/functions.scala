package deconstructinglambdas

import deconstructinglambdas.JSFunc
import deconstructinglambdas.typeclass.*

def thrice[K[_, _]: Category, A](k: K[A, A]): K[A, A] = 
  k >>> (k >>> k)

def add3: Int => Int = 
  thrice((_: Int) + 1)

def times10: JSFunc[Int, Int] =
  JSFunc("(x => x * 10)")

def times1000: JSFunc[Int, Int] =
  thrice(times10)

def isPalindrome[K[_, _]: MyPrimitives: Cartesian: Strong: Category]: K[String, Boolean] =
  val c = Cartesian[K]
  val p = MyPrimitives[K]
  c.copy >>> (p.reverseString.first >>> p.eq)

def isEven[K[_, _]: Numeric: Cartesian: Strong: Choice: Category: MyPrimitives]: K[Int, Boolean] = 
  val n = Numeric[K]
  val p = MyPrimitives[K]
  val mod2 = strong(n.mod, n.num(2))
  mod2 >>> strong(p.eq, n.num(0))