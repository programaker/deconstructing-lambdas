package deconstructinglambdas

import deconstructinglambdas.typeclass.Category

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