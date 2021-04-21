package deconstructinglambdas

import deconstructinglambdas.typeclass.{Cartesian, Category, Strong, MyPrimitives}

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

  given Strong[JSFunc] with
    extension [A, B](k: JSFunc[A, B])
      def first[Other]: JSFunc[(A, Other), (B, Other)] =
        JSFunc(
          s"""([l, r]) => {
            |  const onFirst = ${k.renderJs};
            |  const result = onFirst(l);
            |  return [result, r];
            |}""".stripMargin
        )

      def second[Other]: JSFunc[(Other, A), (Other, B)] =
        JSFunc(
          s"""([l, r]) => {
             |  const onSecond = ${k.renderJs};
             |  const result = onSecond(r);
             |  return [l, result];
             |}""".stripMargin
        )

  given MyPrimitives[JSFunc] with 
    def reverseString: JSFunc[String, String] = 
      JSFunc("""(s => s.split("").reverse().join(""))""")

    def eq[A](using CanEqual[A, A]): JSFunc[(A, A), Boolean] = 
      JSFunc("""(([x, y]) => x === y)""")
