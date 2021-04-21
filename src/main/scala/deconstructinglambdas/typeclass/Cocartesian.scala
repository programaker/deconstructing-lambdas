package deconstructinglambdas.typeclass

trait Cocartesian[K[_, _]: Category]:
  def injectL[A, B]: K[A, Either[A, B]] 
  def injectR[A, B]: K[A, Either[B, A]] 
  def unify[A]: K[Either[A, A], A]

  /** tags 'Right' when 'True', 'Left' when 'False' */
  def tag[A]: K[(Boolean, A), Either[A, A]]

object Cocartesian:
  inline def apply[K[_, _]: Category](k: Cocartesian[K]): Cocartesian[K] = k  
