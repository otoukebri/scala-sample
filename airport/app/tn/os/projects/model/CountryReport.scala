package tn.os.projects.model

import play.api.libs.json.Json

case class CountryReport(country: Country, count: Int)

object CountryReport {
  implicit val format = Json.format[CountryReport]
}
