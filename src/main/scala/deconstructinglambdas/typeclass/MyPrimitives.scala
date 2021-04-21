package deconstructinglambdas.typeclass

trait MyPrimitives[K[_, _]]:
  def reverseString: K[String, String]
  def eq[A](using CanEqual[A, A]): K[(A, A), Boolean] 

object MyPrimitives:
  inline def apply[K[_, _]](using k: MyPrimitives[K]): MyPrimitives[K] = k 

  given MyPrimitives[Function] with
    def reverseString: String => String = _.reverse
    def eq[A](using CanEqual[A, A]): ((A, A)) => Boolean = _ == _
