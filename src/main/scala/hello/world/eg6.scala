package hello.world

import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.native._
import scala.scalajs.js.Dynamic.literal

@react class PizzaTranslator extends Component {
  type Props = Unit
  case class State(inputText:String)
  def initialState: State = State("")

  def render(): ReactElement = {
    View(
      style = centerStyle
    )(
      TextInput(
        placeholder = "Type here to translate!", //what happens to the initialState = State("") GC'd??
        onChangeText = { text:String => setState(State(text)) } //state is replaced with the next state
      ),
      Text("Translation"),
      Text {
        if(state.inputText.length != 0) {
          val v1: String = state.inputText
          val v2: Array[String] = v1.split(" ")
          val v3: Array[String] = v2.map(w => "🍕")
          val v4: String = v3.mkString(" ")
          v4
        }
        else "--"
      }
    )
  }
}

/** problems -  */

object eg6 {
  def apply() = PizzaTranslator
}