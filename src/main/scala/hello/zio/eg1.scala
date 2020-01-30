package hello.zio

import hello.world.centerStyle
import slinky.core.{FunctionalComponent, ReactComponentClass}
import slinky.core.annotations.react
import slinky.core.facade.Hooks.useState
import slinky.native.{Button, Text, View}
import zio.clock.Clock

/** something was needed to run a zio subsystem
 * alongside the root RN component, ie, `StateHook` in this case */
object ZioRoot {

  import zio._
  import duration._

  /** state is a plain var
   * because we need to dereference it from `object StateHook` down there
   * and if we used a zio reference primitive instead
   * it would complicate the dereferencing
   * .
   * var isn't recommended in concurrent settings, but
   * in this application of var, we restrict ourselves in the code
   * this var would be written only from an agent in the zioland.
   * that agent instead would be responsible for managing concurrency via zio */
  var state = 0
  val sch   = Schedule.spaced(1.second)
  val zrts  = new DefaultRuntime {}

  /** (!) zprg is always runs. the unit it declares to return
   * is never returned actually. */
  val zprg: ZIO[Clock, Throwable, Unit] =
    for {
      _   <-  Task(state += 1).repeat(sch) /** this zprg just increments the state by 1 per second */
    } yield ()

  /** we needed to wrap this kick off the execution of the zio runtime
   * in some nonzio concurrency primitive as zio is another land in itself
   * where we need to run it
   * .
   * using future here solves two purpose
   * 1 - it kicks off the evaluation of zprg on a different logical executor
   * 2 - `val kick` returns immediately so that our react land can be bootstrapped */
  import scala.concurrent._
  val kick  = Future(zrts.unsafeRun(zprg))(ExecutionContext.Implicits.global)

}

import ZioRoot._
@react object StateHook {

  val component = FunctionalComponent[Unit] { _ =>

    val (count, setCount) = useState(0)
    View(style = centerStyle)(
      Text(s"you clicked $count times"),
      Button(
        title   = "Read Seconds Elapsed",
        onPress = () => setCount(state)
        /** onPress sets count to the value read from `state0` var
         * which is concurrently being mutated by the zio subsystem running concurrently */
      )
    )
  }
}

/** this gets exported via `@JSExportTopLevel("app")`
 * the `StateHook.component` picks up the reference state0
 * which is outside the enclosing component object.
 * We don't tell fastOptJS to export it explicitly.
 * Instead it picks them up along, as they are needed
 * to make `StateHook.component` work. */
object eg1 {
  def apply(): ReactComponentClass[_] = StateHook.component
}
