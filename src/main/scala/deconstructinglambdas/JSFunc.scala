package deconstructinglambdas

import deconstructinglambdas.typeclass.{Cartesian, Category}

final case class JSFunc[A, B](renderJs: String)

object JSFunc:
  given Category[JSFunc] with
    def id[X](x: X): JSFunc[X, X] =
      JSFunc("(x => x)")

    extension [X, Y](f: JSFunc[X, Y])
      def >>>[Z](g: JSFunc[Y, Z]): JSFunc[X, Z] =
        JSFunc(
          s"""(input) => {
            |  const fst = ${f.renderJs};
            |  const snd = ${g.renderJs};
            |  return snd(fst(input));
            |}""".stripMargin
        )

  given Cartesian[JSFunc] with
    def copy[A]: JSFunc[A, (A, A)] = JSFunc("(x => ([x, x]))")
    def consume[A]: JSFunc[A, Unit] = JSFunc("(x => null)")
    def fst[L, R]: JSFunc[(L, R), L] = JSFunc("(([x, _]) => x)")
    def snd[L, R]: JSFunc[(L, R), R] = JSFunc("(([_, y]) => y)")