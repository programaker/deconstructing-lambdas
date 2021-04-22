package deconstructinglambdas

import deconstructinglambdas.typeclass.*

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

  given Cocartesian[JSFunc] with 
    def injectL[A, B]: JSFunc[A, Either[A, B]] =
      JSFunc("(x => ({tag: 'left', value: x}))")

    def injectR[A, B]: JSFunc[A, Either[B, A]] = 
      JSFunc("(x => ({tag: 'right', value: x}))")

    def unify[A]: JSFunc[Either[A, A], A] =
      JSFunc("(x => (x.value))")

    def tag[A]: JSFunc[(Boolean, A), Either[A, A]] =
      JSFunc("(([b, x]) => ({tag: b ? 'right' : 'left', value: x}))")

  given Choice[JSFunc] with
    extension [A, B](k: JSFunc[A, B])
      def left[Other]: JSFunc[Either[A, Other], Either[B, Other]] =
        JSFunc(
          s"""(input) => {
          |  const overLeft = ${k.renderJs};
          |  if (input.tag == 'left') {
          |    return { tag: 'left', value: overLeft(input.value) };
          |  }
          |  return input;
          |}""".stripMargin
        )

      def right[Other]: JSFunc[Either[Other, A], Either[Other, B]] =
        JSFunc(
          s"""(input) => {
          |  const overRight = ${k.renderJs};
          |  if (input.tag == 'right') {
          |    return { tag: 'right', value: overRight(input.value) };
          |  }
          |  return input;
          |}""".stripMargin
        )

  given Numeric[JSFunc] with
    def num[A](i: Int): JSFunc[A, Int] = JSFunc(s"(x => $i)")
    def negate: JSFunc[Int, Int] = JSFunc("(x => -x)")
    def add: JSFunc[(Int, Int), Int] = JSFunc("(([x, y]) => x + y)")
    def mult: JSFunc[(Int, Int), Int] = JSFunc("(([x, y]) => x * y)")
    def div: JSFunc[(Int, Int), Int] = JSFunc("(([x, y]) => x / y)")
    def mod: JSFunc[(Int, Int), Int] = JSFunc("(([x, y]) => x % y)")
