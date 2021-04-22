package deconstructinglambdas.typeclass

trait Symmetric[K[_, _]: Category]:
  def swap[L, R]: K[(L, R), (R, L)]
  def swapE[L, R]: K[Either[L, R], Either[R, L]]
  def reassoc[A, B, C]: K[(A, (B, C)), ((A, B), C)]  
  def reassocE[A, B, C]: K[Either[A, Either[B, C]], Either[Either[A, B], C]]
  
object Symmetric:
  inline def apply[K[_, _]: Category](using k: Symmetric[K]): Symmetric[K] = k 
  
  given Symmetric[Function] with 
    def swap[L, R]: ((L, R)) => (R, L) = 
      (l, r) => (r, l)
    
    def swapE[L, R]: Either[L, R] => Either[R, L] = 
      _ match
        case Left(l) => Right(l)
        case Right(r) => Left(r)
    
    def reassoc[A, B, C]: ((A, (B, C))) => ((A, B), C) = 
      _ match
        case (a, (b, c)) => ((a, b), c)
    
    def reassocE[A, B, C]: Either[A, Either[B, C]] => Either[Either[A, B], C] = 
      _ match
        case Left(a) => Left(Left(a))
        case Right(Left(b)) => Left(Right(b)) 
        case Right(Right(c)) => Right(c)
