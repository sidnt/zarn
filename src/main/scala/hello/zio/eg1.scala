package hello.zio

import hello.world.centerStyle
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.ReactElement
import slinky.native._

@react object FunComp {
  val component = FunctionalComponent[Unit] { _ =>
    View(style = centerStyle)(Text("ℍ\uD835\uDD56\uD835\uDD5D\uD835\uDD5D\uD835\uDD60. \uD835\uDD3D\uD835\uDD63\uD835\uDD60\uD835\uDD5E \uD835\uDD52 \uD835\uDD3D\uD835\uDD66\uD835\uDD5F\uD835\uDD54\uD835\uDD65\uD835\uDD5A\uD835\uDD60\uD835\uDD5F\uD835\uDD52\uD835\uDD5D ℂ\uD835\uDD60\uD835\uDD5E\uD835\uDD61\uD835\uDD60\uD835\uDD5F\uD835\uDD56\uD835\uDD5F\uD835\uDD65."))
  }
}
//
//@react class FunCompLoader extends StatelessComponent {
//  type Props = Unit
//  def render(): ReactElement =
//    FunComp.component.apply(()).withKey("somekey")
//}

object eg1 {
//  def apply() = FunCompLoader.componentConstructor
  def apply(): ReactComponentClass[_] = FunComp.component
}
