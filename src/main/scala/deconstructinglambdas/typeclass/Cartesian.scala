package deconstructinglambdas.typeclass

trait Cartesian[K[_, _]: Category]:
  def copy[A]: K[A, (A, A)]
  def consume[A]: K[A, Unit]
  def fst[L, R]: K[(L, R), L]
  def snd[L, R]: K[(L, R), R]

object Cartesian:
  inline def apply[K[_, _]: Cartesian]: Cartesian[K] = summon

  given Cartesian[Function] with
    def copy[A]: A => (A, A) = a => (a, a)
    def consume[A]: A => Unit = a => ()
    def fst[L, R]: ((L, R)) => L = (l, r) => l
    def snd[L, R]: ((L, R)) => R = (l, r) => r
