import scala.io.Source
import scala.util.{Success, Try}

/**
  * Created by dskrvk on 11/11/15.
  */
object WeatherData
  extends App {

  val daySpreadTuples =
    Source.fromFile(args(0)).getLines()
      .drop(2) // skip header lines
      .map(_.split(' ')
            .filterNot(_.isEmpty)
            .take(3) // only need first 3 columns
            .map(s => Try(Integer.parseInt(s))))
      .flatMap(_ match {
        case Array(Success(n), Success(max), Success(min)) => Some((n, max - min))
        case _ => None
      })

  val first = daySpreadTuples.next()
  val dayWithSmallestSpread =
      daySpreadTuples.foldLeft(first) { (smallestSpreadTuple, dayTuple) =>
        if (smallestSpreadTuple._2 < dayTuple._2)
          smallestSpreadTuple
        else
          dayTuple
      }

  println(s"The day with the smallest temperature spread is ${dayWithSmallestSpread._1}")
}
