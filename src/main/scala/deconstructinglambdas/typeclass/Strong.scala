package deconstructinglambdas.typeclass

trait Strong[K[_, _]: Category]:
  extension [A, B](k: K[A, B])
    def first[Other]: K[(A, Other), (B, Other)]
    def second[Other]: K[(Other, A), (Other, B)]

object Strong:
  def apply[K[_, _]: Category](using k: Strong[K]): Strong[K] = k