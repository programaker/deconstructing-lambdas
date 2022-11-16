package deconstructinglambdas.typeclass

trait Cocartesian[K[_, _]]:
  def injectL[A, B]: K[A, Either[A, B]]
  def injectR[A, B]: K[A, Either[B, A]]
  def unify[A]: K[Either[A, A], A]

  /** tags 'Right' when 'True', 'Left' when 'False' */
  def tag[A]: K[(Boolean, A), Either[A, A]]

object Cocartesian:
  inline def apply[K[_, _]: Cocartesian]: Cocartesian[K] = summon

  given Cocartesian[Function] with
    def injectL[A, B]: A => Either[A, B] = Left(_)
    def injectR[A, B]: A => Either[B, A] = Right(_)
    def unify[A]: Either[A, A] => A = _.fold(identity, identity)

    def tag[A]: ((Boolean, A)) => Either[A, A] =
      _ match
        case (true, a)  => Right(a)
        case (false, a) => Left(a)
