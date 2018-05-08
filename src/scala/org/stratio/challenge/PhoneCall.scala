package scala.org.stratio.challenge

/**
 * @author sramirez
 */
class PhoneCall(var duration: Int, var phoneNumber: Long) extends Ordered[PhoneCall] {

  /**
   * First, phone calls are compared by duration, and then by reverse phone number.
   */
  override def compare(that: PhoneCall) = {
    val durationC = this.duration.compare(that.duration)
    if(durationC == 0) -this.phoneNumber.compare(that.phoneNumber) else durationC
  }
}