package tn.os.projects.dao

import tn.os.projects.model.Country

trait CountryDao {
  def countries : List[Country]
}