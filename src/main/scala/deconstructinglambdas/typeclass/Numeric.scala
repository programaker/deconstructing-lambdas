package deconstructinglambdas.typeclass

trait Numeric[K[_, _]]:
  def num[A](i: Int): K[A, Int]
  def negate: K[Int, Int]
  def add: K[(Int, Int), Int]
  def mult: K[(Int, Int), Int]
  def div: K[(Int, Int), Int]
  def mod: K[(Int, Int), Int]

object Numeric:
  inline def apply[K[_, _]](using k: Numeric[K]): Numeric[K] = k

  given Numeric[Function] with
    def num[A](i: Int): A => Int = _ => i
    def negate: Int => Int = i => -i
    def add: ((Int, Int)) => Int = _ + _
    def mult: ((Int, Int)) => Int = _ * _
    def div: ((Int, Int)) => Int = _ / _
    def mod: ((Int, Int)) => Int = _ % _
