import scala.util.{Success, Try}

/**
  * Created by dskrvk on 11/11/15.
  */
object SoccerLeagueTable
  extends App {

  val resultsTuples = new DsvFileParser(args(0), 1, ' ')
    .parse()
    .flatMap({
      case Array(_, team, _, _, _, _, wins, _, losses, _) =>
        (team, Try(Integer.parseInt(wins)), Try(Integer.parseInt(losses))) match {
          case (t, Success(w), Success(l)) => Some((t, w - l))
          case _ => None
        }
      case _ => None
    })

  val teamWithSmallestSpread = resultsTuples.minBy({case (team, spread) => spread})

  println(s"The team with the smallest goal spread is ${teamWithSmallestSpread._1}")
}
