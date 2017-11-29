package tn.os.projects.dao

import tn.os.projects.model.Runway
import tn.os.projects.business.utils.CsvReader

class RunwayDaoStub extends RunwayDao {
  val fileName: String = "conf/csv/runways.csv"
  override def runways: List[Runway] = new CsvReader(fileName)
    .readAndParse((line, headers) => Runway(line, headers))
}