package scala.org.stratio.challenge
import org.junit.runners.Suite
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfterAll
import org.scalatest.junit.JUnitRunner

/**
 * Test for Stratio Challenge (phone bill)
 *
 * @author Sergio Ramirez
 */
@RunWith(classOf[JUnitRunner])
class StratioChallengeSuite extends FunSuite {

  test("Run the test given in the requirements (1st test)") {

    assertResult(900) {
      Stratio.computePhoneBill("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090")
    }
  }
  
  test("Run test with two phone numbers and the same total call duration") {

    assertResult(1098) {
      Stratio.computePhoneBill("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090\n00:01:06,701-080-080")
    }
  }
  
  test("Run test with a single call (free promotion)") {

    assertResult(0) {
      Stratio.computePhoneBill("00:05:00,701-080-080")
    }
  }
  
  test("Run test with two phone numbers, no-promotion number has a single call (300 seconds) ") {

    assertResult(750) {
      Stratio.computePhoneBill("00:06:00,400-234-090\n00:05:00,701-080-080")
    }
  }
  
  test("Run test with two phone numbers, no-promotion number has a single call (299 seconds) ") {

    assertResult(897) {
      Stratio.computePhoneBill("00:06:00,400-234-090\n00:04:59,701-080-080")
    }
  }
}