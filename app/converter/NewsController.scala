package converter

import javax.inject.Inject
import play.api.libs.json.{Format, Json, Writes}
import play.api.mvc.{Action, AnyContent}

import scala.concurrent.ExecutionContext

class NewsController @Inject()(cc: NewsControllerComponents, newsMapper: NewsMapper)(
                              implicit ec: ExecutionContext)
                              extends NewsBaseController(cc)
{
  implicit val newsResourceFormat: Format[NewsResource] = Json.format[NewsResource]
  implicit val listNewsResource: Writes[Seq[NewsResource]] = Writes.seq(newsResourceFormat)

  def fetch(query: String): Action[AnyContent] = NewsAction.async { implicit request =>
    newsResourceHandler.fetch(query).map { news => Ok(Json.toJson(newsMapper.toRequests(news
    )))}
  }
}
