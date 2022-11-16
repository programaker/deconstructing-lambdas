package deconstructinglambdas

import deconstructinglambdas.JSFunc
import deconstructinglambdas.typeclass.*

object functions:
  def thrice[K[_, _]: Category, A](k: K[A, A]): K[A, A] =
    k >>> k >>> k

  def isPalindrome[K[_, _]: Category: Cartesian: Strong: MyPrimitives]: K[String, Boolean] =
    val p = MyPrimitives[K]
    Cartesian[K].copy >>> (p.reverseString.first >>> p.eq)

  def isEven[K[_, _]: Category: Cartesian: Strong: Choice: Numeric: MyPrimitives]: K[Int, Boolean] =
    val n = Numeric[K]
    val mod2 = strong(n.mod, n.num(2))
    mod2 >>> strong(MyPrimitives[K].eq, n.num(0))

  /** WTF is a Collatz? See https://en.wikipedia.org/wiki/Collatz_conjecture */
  def collatzStep[K[_, _]: Category: Cartesian: Choice: Strong: Cocartesian: Numeric: MyPrimitives]: K[Int, Int] =
    val n = Numeric[K]
    val onOdds = strong(n.mult, n.num(3)) >>> strong(n.add, n.num(1))
    val onEvens = strong(n.div, n.num(2))
    matchOn(isEven) >>> (onOdds +++ onEvens) >>> Cocartesian[K].unify
