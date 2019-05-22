package converter

import play.api.mvc.{BaseController, ControllerComponents}

class NewsBaseController(cc: NewsControllerComponents) extends BaseController with RequestMarkerContext {
  override protected def controllerComponents: ControllerComponents = cc

  def NewsAction: NewsActionBuilder = cc.newsActionBuilder

  def newsResourceHandler: NewsResourceHandler = cc.newsResourceHandler
}
