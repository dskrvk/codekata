import scala.io.Source

/**
  * Parses a delimiter-separated (DSV) file into arrays of strings.
  * Treats multiple consecutive delimiters as one.
  * Skips the header rows.
  * Created by dskrvk on 11/11/15.
  */
class DsvFileParser(filePath: String, numHeaderRows: Int, separator: Char) {
  def parse() = {
    Source.fromFile(filePath).getLines()
      .drop(numHeaderRows)
      .map(_.split(separator)
            .filterNot(_.isEmpty))
  }
}
