package converter

import javax.inject.Inject
import play.api.{Logger, MarkerContext}
import play.api.http.HttpVerbs
import play.api.i18n.MessagesApi
import play.api.mvc.{ActionBuilder, AnyContent, BodyParser, PlayBodyParsers, Request, Result}

import scala.concurrent.{ExecutionContext, Future}

class NewsActionBuilder @Inject() (messagesApi: MessagesApi,
                                   playBodyParsers: PlayBodyParsers)(
                                  implicit val executionContext: ExecutionContext)
  extends ActionBuilder[PostRequest, AnyContent]
  with RequestMarkerContext
  with HttpVerbs
{
  override val parser: BodyParser[AnyContent] = playBodyParsers.anyContent

  type PostRequestBlock[A] = PostRequest[A] => Future[Result]

  private val logger = Logger(this.getClass)

  override def invokeBlock[A](request: Request[A],
                              block: PostRequestBlock[A]): Future[Result] = {
    // Convert to marker context and use request in block
    implicit val markerContext: MarkerContext = requestHeaderToMarkerContext(
      request)
    logger.trace(s"invokeBlock: ")

    val future = block(new PostRequest(request, messagesApi))

    future.map { result =>
      request.method match {
        case GET | HEAD =>
          result.withHeaders("Cache-Control" -> s"max-age: 100")
        case other =>
          result
      }
    }
  }
}
