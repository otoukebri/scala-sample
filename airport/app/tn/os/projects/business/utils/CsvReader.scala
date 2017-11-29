package tn.os.projects.business.utils

import scala.io.Source

class CsvReader(fileName: String) {
    private var headers: Map[String, Int] = Map.empty

  def getHeaders: Map[String, Int] = headers


  def readAndParse[A](converter: (String, Map[String, Int]) => A): List[A] = {
    val lines = Source
      .fromFile(fileName)
      .getLines()
      .toList

    lines.take(1)
      .toList
      .map(_.split(",", -1).toList.map(_.replaceAll("\"", "").trim).zipWithIndex.toMap)
      .headOption
      .foreach(headers = _)

    lines.drop(1)
      .map(converter(_, getHeaders))
  }
}