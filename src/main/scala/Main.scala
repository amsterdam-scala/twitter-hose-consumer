import com.danielasfregola.twitter4s.entities.{RatedData, StatusSearch}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  import com.danielasfregola.twitter4s.TwitterRestClient
//  import com.danielasfregola.twitter4s.TwitterStreamingClient

  val restClient = TwitterRestClient()
//  val streamingClient = TwitterStreamingClient()


  while(true) {
    val result: Future[RatedData[StatusSearch]] = restClient.searchTweet(
      query = "danielasfregola"
    )
    result.foreach { foo: RatedData[StatusSearch] =>
      val RatedData(rate_limit, data) = foo
      data.statuses.map(elem => elem.text.take(50)).foreach { prefix =>
        if(prefix.contains("scala")) {
          println(prefix)
        }
      }
    }

    Thread.sleep(30000)
  }

  println("Hello world!")
}