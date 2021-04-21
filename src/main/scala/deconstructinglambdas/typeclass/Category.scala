package deconstructinglambdas.typeclass

trait Category[K[_, _]]:
  def id[X](x: X): K[X, X]

  extension [X, Y](k: K[X, Y])
    def >>>[Z](other: K[Y, Z]): K[X, Z]

object Category:
  inline def apply[K[_, _]](using k: Category[K]): Category[K] = k

  given Category[Function] with 
    def id[X](x: X): X => X =
      identity

    extension [X, Y](k: X => Y)
      def >>>[Z](other: Y => Z): X => Z = k andThen other
