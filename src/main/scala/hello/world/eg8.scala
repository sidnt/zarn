package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.native._

@react object StateHook {

  val component = FunctionalComponent[Unit] { _ =>
    val (count, setCount) = useState(0)
    View(style = centerStyle)(
      Text(s"you clicked $count times"),
      Button(
        title   = "Count++",
        onPress = () => setCount(_+1)
      )
    )
  }
}

object eg8 {
  def apply(): ReactComponentClass[_] = StateHook.component
}
