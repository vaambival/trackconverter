package converter

import play.api.i18n.MessagesApi
import play.api.mvc.{MessagesRequestHeader, PreferredMessagesProvider, Request, WrappedRequest}

trait PostRequestHeader
  extends MessagesRequestHeader
    with PreferredMessagesProvider
class PostRequest[A](request: Request[A], val messagesApi: MessagesApi)
  extends WrappedRequest(request)
    with PostRequestHeader
