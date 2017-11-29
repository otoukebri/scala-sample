package tn.os.projects.controllers

import scala.concurrent.ExecutionContext

import javax.inject.Inject
import play.api.mvc.AbstractController
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.ControllerComponents
import tn.os.projects.business.QueryBusiness
import play.api.libs.json.Json


class QueryController @Inject() (cc: ControllerComponents, queryBusiness: QueryBusiness)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  
  /**
   * retrieve airport and runways using country name or country code
   */  
  def searchCountry (pattern:String) : Action[AnyContent] = Action.async {
    queryBusiness.search(pattern).map { result =>
      Ok(Json.toJson(result))
    }.recover {
      case ex => Ok(ex.getMessage)
    }  
  }

}