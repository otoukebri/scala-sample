package tn.os.projects.controllers

import play.api.mvc.AbstractController
import scala.concurrent.ExecutionContext
import play.api.mvc.ControllerComponents
import javax.inject.Inject
import scala.concurrent.Future
import tn.os.projects.business.ReportBusiness
import play.api.libs.json.Json

class ReportController @Inject() (cc: ControllerComponents, reportBusiness: ReportBusiness)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  
  /**
   * retrieve the highest Number Airports
   */
  def highestNumberAirports = Action.async {
    reportBusiness.getCountriesWithHighAirportsNumber.map { result =>
      Ok(Json.toJson(result))
    }.recover {
      case ex => Ok(ex.getMessage)
    }  
  }

  
  /**
   * retrieve the lowest Number Airports
   */  
  def lowestNumberAirports = Action.async {
    reportBusiness.getCountriesWithLowestAirportsNumber.map{ result =>
      Ok(Json.toJson(result))
    }.recover {
      case ex => Ok(ex.getMessage)
    }  
  }  
}