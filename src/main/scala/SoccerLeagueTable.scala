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

  val first = resultsTuples.next()
  val teamWithSmallestSpread =
    resultsTuples.foldLeft(first) { (smallestSpreadTuple, teamTuple) =>
      if (smallestSpreadTuple._2 < teamTuple._2)
        smallestSpreadTuple
      else
        teamTuple
    }

  println(s"The team with the smallest goal spread is ${teamWithSmallestSpread._1}")
}
