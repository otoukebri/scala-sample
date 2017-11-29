package tn.os.projects.business

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import javax.inject.Inject
import tn.os.projects.dao.AirportDaoImpl
import tn.os.projects.dao.CountryDaoImpl
import tn.os.projects.dao.RunwayDaoImpl
import tn.os.projects.model.AirportRunways
import tn.os.projects.model.Country
import tn.os.projects.dao.RunwayDao
import tn.os.projects.dao.CountryDao
import tn.os.projects.dao.AirportDao

class QueryBusiness @Inject() (countryDao: CountryDao, airportDao: AirportDao, runwayDao: RunwayDao)(implicit ec: ExecutionContext) {

  def search(pattern: String): Future[List[AirportRunways]] = {
    val r = countryDao.countries.find {
      country =>
        country.code.toLowerCase().startsWith(pattern.trim().toLowerCase()) ||
          country.name.toLowerCase().startsWith(pattern.trim().toLowerCase())
    }.map { country =>
      getAirportsRunwaysFromCountryCode(country)
    }.toList
    Future.sequence(r).map(_.flatten)
  }

  private def getAirportsRunwaysFromCountryCode(country: Country): Future[List[AirportRunways]] = Future {
    (for {
      airport <- airportDao.airports.filter(_.isoCountry == country.code)
      ar = AirportRunways(airport, runwayDao.runways.filter(_.airportRef == airport.id).toList)
    } yield (ar)).toList
  }
}