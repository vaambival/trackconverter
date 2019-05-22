package converter

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class NewsRouter @Inject()(controller: NewsController) extends SimpleRouter {

  override def routes: Routes = {
    case GET(p"/get-new-requests" ? q"query=$query") =>
      controller.fetch(query: String)
  }
}
