package hello.world

import scala.scalajs.js
import scala.scalajs.LinkingInfo
import scala.scalajs.js.annotation.{JSExportTopLevel, JSImport}
import slinky.native.AppRegistry
import slinky.hot

/** interestingly, this Main doesn't extend the App trait
 * so means it's not runnable on the jvm because there's no main
 * but this does export a top level js app, which is like the runnable app
 * on the js platform */
object Main {
  /** this side effect would always be executed,
   * first thing during the construction of object Main in the runtime
   * but is it the JVM runtime or the JS runtime?
   * it depends on the fastOptJS task.
   * seems like fastOptJS is run on JVM, as it's run via sbt on jvm
   * to export the main js top level runnable module to disk */
  if (LinkingInfo.developmentMode) {
    hot.initialize()
  }

  @JSExportTopLevel("app")
  val app =
    //App.componentConstructor
    //eg0().componentConstructor
    //eg1().componentConstructor
    //eg2().componentConstructor
    //eg3().componentConstructor
    //eg4().componentConstructor
    //eg5.a().componentConstructor
    //eg5.b().componentConstructor
    //eg6().componentConstructor
    //eg7.a().componentConstructor
//    eg8()
//    hello.zio.eg0()
    hello.zio.eg1()
}
