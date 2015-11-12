import scala.util.{Success, Try}

/**
  * Created by dskrvk on 11/11/15.
  */
object WeatherData
  extends App {

  val daySpreadTuples =
    new DsvFileParser(args(0), 2, ' ')
      .parse()
      .flatMap({
        case Array(week, max, min, _*) =>
          (week, Try(Integer.parseInt(max)), Try(Integer.parseInt(min))) match {
            case (w, Success(mx), Success(mn)) => Some((w, mx - mn))
            case _ => None
          }
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
