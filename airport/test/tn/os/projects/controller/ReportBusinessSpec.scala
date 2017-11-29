package tn.os.projects.controller

import org.scalatest.Matchers
import org.scalatest.WordSpecLike
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import tn.os.projects.business.QueryBusiness
import javax.inject.Inject
import tn.os.projects.dao.RunwayDaoStub
import tn.os.projects.dao.AirportDaoStub
import tn.os.projects.dao.CountryDaoStub
import scala.concurrent.Await
import scala.concurrent.duration._
import tn.os.projects.business.ReportBusiness

class ReportBusinessSpec extends WordSpecLike with Matchers {

  val reportBusiness = new ReportBusiness(new CountryDaoStub, new AirportDaoStub)
  "Report Business Test" should {
    "get Countries with High Airports Number" in {
      val reportResponse = Await.result(reportBusiness.getCountriesWithHighAirportsNumber, 15 seconds)
      reportResponse.length shouldEqual 10
      reportResponse.head.country.name shouldEqual "United States"
    }
    "get Countries with lowest Airports Number" in {
      val reportResponse = Await.result(reportBusiness.getCountriesWithLowestAirportsNumber, 15 seconds)
      reportResponse.length shouldEqual 10
      reportResponse.head.country.name shouldEqual "United States"
    }
  }

}
