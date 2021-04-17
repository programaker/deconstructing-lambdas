package deconstructinglambdas.typeclass

trait Category[K[_, _]]:
  def id[X](x: X): K[X, X]

  extension [Y, Z](k: K[Y, Z])
    def compose[X](other: K[X, Y]): K[X, Z]

  extension [X, Y](k: K[X, Y])
    def >>>[Z](other: K[Y, Z]): K[X, Z]

object Category:
  def apply[K[_, _]](using k: Category[K]): Category[K] = k

  given Category[Function] with 
    def id[X](x: X): X => X = identity

    extension [Y, Z](k: Y => Z)
      def compose[X](other: X => Y): X => Z = k compose other

    extension [X, Y](k: X => Y)
      def >>>[Z](other: Y => Z): X => Z = k andThen other
