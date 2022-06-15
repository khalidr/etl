package tv.sixzerofive
import cats.MonadError
import org.apache.spark.sql.{DataFrame, Dataset}
import tv.sixzerofive.shapes._
import cats.implicits.toFlatMapOps
import cats.implicits.toFunctorOps

trait RunnablePipeline[Result, E] {
  def run[F[_]]()(implicit F: MonadError[F, E]): F[Result]
}

object MyPipeline extends RunnablePipeline[Int, Throwable] {

  override def run[F[_]]()(implicit F: MonadError[F, Throwable]): F[Int] =
    for {
      extract ← MyExtract()
      transform ← MyTransform(extract)
      resultCount ← MyLoad(transform)
    } yield resultCount
}

case class Person(name: String, email: String)

object MyExtract extends Extract[DataFrame, Throwable] {
  override def apply[In[_]]()(implicit In: MonadError[In, Throwable]): In[DataFrame] = ???
}

object MyTransform extends Transform[DataFrame, Dataset[Person], Throwable ] {
  override def apply[Out[_]](in: DataFrame)(implicit Out: MonadError[Out, Throwable]): Out[Dataset[Person]] = ???
}

object MyLoad extends Load[Dataset[Person], Int, Throwable] {
  override def apply[Out[_]](in: Dataset[Person])(implicit Out: MonadError[Out, Throwable]): Out[Int] = ???
}

//pipeline consists of E T L components
// there can be multiple of each component
// that will be composed in pipeline
