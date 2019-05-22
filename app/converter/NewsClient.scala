package converter

import javax.inject.Inject
import play.api.MarkerContext
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

class NewsClient @Inject()(ws: WSClient)(implicit ec: ExecutionContext) {
  val url ="http://192.168.1.132:8083/api/v1/news/search"

  def fetch(query: String)(implicit mc: MarkerContext): Future[WSResponse] = {
    val request: WSRequest = ws.url(url)
    val complexRequest: WSRequest = request
      .addQueryStringParameters("query" -> query)
        .addQueryStringParameters("fromDb" -> "true")
    complexRequest.get()
  }
}
