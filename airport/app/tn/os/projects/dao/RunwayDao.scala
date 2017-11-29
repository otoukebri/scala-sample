package tn.os.projects.dao

import tn.os.projects.model.Runway

trait RunwayDao {
  def runways: List[Runway] 
}