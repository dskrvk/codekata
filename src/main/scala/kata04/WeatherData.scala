package kata04

import scala.util.{Success, Try}

/**
  * Created by dskrvk on 11/11/15.
  */
object WeatherData
  extends KataApp {

  val daySpreadTuples = new DsvFileParser(inputFile(), 2, ' ')
    .parse()
    .flatMap({
      case Array(week, max, min, _*) =>
        (week, Try(Integer.parseInt(max)), Try(Integer.parseInt(min))) match {
          case (w, Success(mx), Success(mn)) => Some((w, mx - mn))
          case _ => None
        }
      case _ => None
    })

  if (daySpreadTuples.isEmpty) {
    println("The file is empty or in the wrong format!")
    System.exit(1)
  }
  val dayWithSmallestSpread = daySpreadTuples.minBy({case (week, spread) => spread})

  println(s"The day with the smallest temperature spread is ${dayWithSmallestSpread._1}")
}
