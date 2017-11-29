package tn.os.projects.dao

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import javax.inject.Inject
import tn.os.projects.business.utils.CsvReader
import tn.os.projects.model.Airport
import tn.os.projects.model.Country

class AirportDaoImpl (implicit ec: ExecutionContext) extends AirportDao {
  val fileName: String = "conf/csv/airports.csv"
  override def airports: List[Airport] = new CsvReader(fileName)
    .readAndParse((line, headers) => Airport(line, headers))
}