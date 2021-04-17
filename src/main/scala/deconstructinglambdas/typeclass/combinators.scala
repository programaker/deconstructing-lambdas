import deconstructinglambdas.typeclass.{Cartesian, Category, Strong}

extension [K[_, _]: Category: Strong, L, L1](l: K[L, L1])
  def ***[R, R1](r: K[R, R1]): K[(L, R), (L1, R1)] = l.first >>> r.second

extension [K[_, _]: Category: Cartesian: Strong, A, L](l: K[A, L])
  def &&&[R](r: K[A, R]): K[A, (L, R)] = Cartesian[K].copy >>> (l *** r)
