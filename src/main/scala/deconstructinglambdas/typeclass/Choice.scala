package deconstructinglambdas.typeclass

trait Choice[K[_, _]: Category]:
  extension [A, B](k: K[A, B])
    def left[Other]: K[Either[A, Other], Either[B, Other]]
    def right[Other]: K[Either[Other, A], Either[Other, B]]
