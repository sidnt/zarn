package example

import zio.App
import zio.console._

object HelloZio {

  def run(args: List[String]) =
    program.fold(_ => 1, _ => 0)

  val program =
    for {
      _ <- putStrLn("Hello! What is your name?")
      n <- getStrLn
      _ <- putStrLn(s"Hello " + n + ", welcome to ZIO!")
    } yield ()

}
