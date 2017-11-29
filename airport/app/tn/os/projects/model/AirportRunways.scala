package tn.os.projects.model

import play.api.libs.json.Json
import play.api.libs.json.OFormat

case class AirportRunways (airport: Airport, runways: List[Runway])

object AirportRunways {
  implicit val format: OFormat[AirportRunways] = Json.format[AirportRunways]
}