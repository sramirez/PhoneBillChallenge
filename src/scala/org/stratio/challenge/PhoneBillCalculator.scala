package scala.org.stratio.challenge

/**
 * @author Sergio Ramírez
 */
object Stratio extends App {
  
  
  def aggregateTime(prev: Int, next: Int) = prev * 60 + next
  
  def computePhoneBill(callLog: String) = {
    
    /** Format calls, from string to PhoneCall objects **/
    val rawCalls = callLog.split("\n")
    val calls = rawCalls.map{ s =>
      
      val parts = s.split(',')
      val call = parts.size match {
        case 2  => 
          val seconds = parts(0).split(":").map(_.toInt).reduceLeft(aggregateTime)
          val number = parts(1).replace("-", "").toLong
          new PhoneCall(seconds, number)
        case _ => 
          new PhoneCall(0, Long.MinValue)
      }
      call      
    }
    println("Number of phone calls: " + calls.size)
    
    /** Find the most-frequent phone number in the log (longest total duration and smallest phone number) **/
    val mumNumber = calls
        .groupBy(_.phoneNumber)
        .mapValues(_.map(_.duration).sum)
        .map{ case (number, duration) => new PhoneCall(duration, number) }
        .max // maximum total duration, if tie smallest number
    println("Phone number with free promotion: " + mumNumber.phoneNumber)

    /** Compute total cost for each phone call, always applying correspondent discounts and tariffs **/
    val total = calls.map { c =>
      c.phoneNumber match {
        case l if l == mumNumber.phoneNumber | l == Long.MinValue => 0
        case _ => if(c.duration < 300) c.duration * 3 else math.ceil(c.duration.toFloat / 60) * 150        
      }
      
    }.sum
    println("Total: " + total)
    total
  }
}