package tv.sixzerofive

import cats.MonadError

object shapes {

  /*
      ┌──────┐
      │      │
      │source├───►
      │      │
      └──────┘
 */

  trait Source[A, E] {
    def apply[In[_]]()(implicit In: MonadError[In, E]): In[A]
  }

  /*
       ┌──────┐
       │      │
    ──►│flow  ├───►
       │      │
       └──────┘

   */
  trait Flow[In, B, E] {
    def apply[Out[_]](in: In)(implicit Out: MonadError[Out, E]): Out[B]
  }

  /*
       ┌──────┐
    ──►│      │
       │fanIn ├──►
    ──►│      │
       └──────┘
   */

  trait FanIn[InA, InB, Out[_], C, E] {
    def apply(inA: InA, inB: InB)(implicit Out: MonadError[Out, E]): Out[C]
  }

  trait Extract[A, E] extends Source[A, E]

  trait Transform[In, B, E] extends Flow[In, B, E]

  trait BiTransform[InA, InB, Out[_], C, E] extends FanIn[InA, InB, Out, C, E]

  trait Load[Out, B, E] extends Flow[Out, B, E]
}
