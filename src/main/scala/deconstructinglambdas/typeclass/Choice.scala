package deconstructinglambdas.typeclass

trait Choice[K[_, _]]:
  extension [A, B](k: K[A, B])
    def left[Other]: K[Either[A, Other], Either[B, Other]]
    def right[Other]: K[Either[Other, A], Either[Other, B]]

object Choice:
  inline def apply[K[_, _]: Choice]: Choice[K] = summon

  given Choice[Function] with
    extension [A, B](k: A => B)
      def left[Other]: Either[A, Other] => Either[B, Other] = _.left.map(k)
      def right[Other]: Either[Other, A] => Either[Other, B] = _.map(k)
