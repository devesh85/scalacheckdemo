package Demo.scalacheck

/**
 * Created by devesh on 9/27/15.
 */
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object ScalaCheckDemo extends Properties("Demo") {

  property("myprop") = forAll { l: List[Int] =>
    l.reverse.reverse == l
  }

  property("AddRemove") = forAll{ (a:Int, l:List[Int]) =>
    (a::l).tail == l
  }

}
