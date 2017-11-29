package tn.os.projects.dao

import tn.os.projects.business.utils.CsvReader
import tn.os.projects.model.Country

class CountryDaoStub extends CountryDao {
  val fileName: String = "conf/csv/countries.csv"
  override def countries: List[Country] = new CsvReader(fileName)
    .readAndParse((line, headers) => Country(line, headers))
}