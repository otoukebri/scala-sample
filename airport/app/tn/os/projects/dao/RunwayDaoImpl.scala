package tn.os.projects.dao

import scala.concurrent.Future

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import tn.os.projects.business.utils.CsvReader
import tn.os.projects.model.Airport
import tn.os.projects.model.Runway

class RunwayDaoImpl extends RunwayDao {
  val fileName: String = "conf/csv/runways.csv"
  override def runways: List[Runway] = new CsvReader(fileName)
    .readAndParse((line, headers) => Runway(line, headers))
}