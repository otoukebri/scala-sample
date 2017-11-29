package tn.os.projects.dao

import tn.os.projects.business.utils.CsvReader
import tn.os.projects.model.Airport

class AirportDaoStub extends AirportDao {

 val fileName: String = "conf/csv/airports.csv"
  override def airports: List[Airport] = new CsvReader(fileName)
    .readAndParse((line, headers) => Airport(line, headers))    
}