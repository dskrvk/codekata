package kata04

import java.io.File

/**
  * An {@link scala.App App} that uses a file (specified in <tt>args(0)</tt>) for input.
  * Created by dskrvk on 11/12/15.
  */
trait KataApp
  extends App {

  def inputFile() = {
    val path = args(0)
    val file = new File(path)
    if (!file.exists || !file.canRead) {
      println(s"Can't open file $path!")
      System.exit(1)
    }
    file
  }
}
