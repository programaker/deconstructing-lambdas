package deconstructinglambdas.typeclass

trait Strong[K[_, _]: Category]:
  extension [A, B](k: K[A, B])
    def first[Other]: K[(A, Other), (B, Other)]
    def second[Other]: K[(Other, A), (Other, B)]

object Strong:
  inline def apply[K[_, _]: Strong: Category]: Strong[K] = summon

  given Strong[Function] with
    extension [A, B](k: A => B)
      def first[Other]: ((A, Other)) => (B, Other) = (a, other) => (k(a), other)
      def second[Other]: ((Other, A)) => (Other, B) = (other, a) => (other, k(a))
