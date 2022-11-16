package deconstructinglambdas.typeclass

import deconstructinglambdas.typeclass.{Cartesian, Category, Strong}

extension [K[_, _]: Category: Strong, L, L1](l: K[L, L1])
  def ***[R, R1](r: K[R, R1]): K[(L, R), (L1, R1)] = l.first >>> r.second

extension [K[_, _]: Category: Cartesian: Strong, A, L](l: K[A, L])
  def &&&[R](r: K[A, R]): K[A, (L, R)] = Cartesian[K].copy >>> (l *** r)

extension [K[_, _]: Category: Choice, L, L1](l: K[L, L1])
  def +++[R, R1](r: K[R, R1]): K[Either[L, R], Either[L1, R1]] = l.left[R] >>> r.right[L1]

def strong[K[_, _]: Category: Cartesian: Strong, A, B, R](f: K[(A, B), R], x: K[A, B]): K[A, R] =
  Cartesian[K].copy >>> x.second >>> f

def matchOn[K[_, _]: Category: Cartesian: Strong: Cocartesian, A](predicate: K[A, Boolean]): K[A, Either[A, A]] =
  Cartesian[K].copy >>> predicate.first >>> Cocartesian[K].tag
