import deconstructinglambdas.typeclass.Category
import Category.given

def thrice[K[_, _]: Category, A](k: K[A, A]): K[A, A] = 
  k >>> k >>> k

def add3: Int => Int = 
  thrice(thrice((_: Int) + 1))
