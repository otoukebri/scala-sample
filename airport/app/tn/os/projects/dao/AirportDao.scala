package tn.os.projects.dao

import tn.os.projects.model.Airport

trait AirportDao {
  def airports : List[Airport]
}