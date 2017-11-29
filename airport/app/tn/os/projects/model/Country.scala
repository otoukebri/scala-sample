package tn.os.projects.model

import tn.os.projects.business.utils.Util._
import play.api.libs.json.Json

case class Country(
                    id: String,
                    code: String,
                    name: String,
                    continent: String,
                    wikipediaLink: String,
                    keywords: String)

object Country {
 implicit val format = Json.format[Country]  
  def apply(line: String, headers: Map[String, Int]): Country = {
    implicit val _headers = headers
    val values = split(line)
    new Country(
      values(get("id")),
      values(get("code")),
      values(get("name")),
      values(get("continent")),
      values(get("wikipedia_link")),
      values(get("keywords")))
  }
}