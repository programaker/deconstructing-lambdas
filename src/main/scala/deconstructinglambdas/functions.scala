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

def isPalindrom[K[_, _]: Category: Cartesian: Strong]: K[String, Boolean] =
  ???
  //Cartesian[K].copy >>> (((_: String).reverse).first)