package deconstructinglambdas.typeclass

trait Cartesian[K[_, _]: Category]:
  def copy[A]: K[A, (A, A)]
  def consume[A]: K[A, Unit]
  def fst[L, R]: K[(L, R), L]
  def snd[L, R]: K[(L, R), R]

object Cartesian:
  def apply[K[_, _]: Category](using k: Cartesian[K]): Cartesian[K] = k