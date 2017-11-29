package tn.os.projects.business

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import javax.inject.Inject
import tn.os.projects.dao.AirportDaoImpl
import tn.os.projects.dao.CountryDaoImpl
import tn.os.projects.model.AirportRunways
import tn.os.projects.model.Country
import tn.os.projects.model.CountryReport
import tn.os.projects.dao.CountryDao
import tn.os.projects.dao.AirportDao

class ReportBusiness @Inject() (countryDao: CountryDao, airportDao: AirportDao)
(implicit ec: ExecutionContext) {

  def getTopCountriesSortedWith(f: (CountryReport, CountryReport) => Boolean) =
    countryReports.sortWith(f).take(10)

  lazy val getCountriesWithHighAirportsNumber: Future[List[CountryReport]] =
    Future.successful(getTopCountriesSortedWith(_.count > _.count))

  lazy val getCountriesWithLowestAirportsNumber: Future[List[CountryReport]] =
    Future.successful(getTopCountriesSortedWith(_.count < _.count))
    
  private lazy val countryReports: List[CountryReport] = {
    (for {
      groupedAirports <- airportDao.airports.groupBy(_.isoCountry)
      airportsCounts = groupedAirports._2.length
      country <- countryDao.countries.filter(_.code == groupedAirports._1)
    } yield {
      CountryReport(country, airportsCounts)
    }).toList
  }


}