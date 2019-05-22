package converter

import javax.inject.{Inject, Provider}
import play.api.MarkerContext
import play.api.libs.ws.WSResponse

import scala.concurrent.{ExecutionContext, Future}

class NewsResourceHandler @Inject()(routerProvider: Provider[NewsRouter],
                                    newsClient: NewsClient)(implicit ec: ExecutionContext)
{
  def fetch(query: String)(implicit mc: MarkerContext): Future[WSResponse] = {
    newsClient.fetch(query)
  }

}
