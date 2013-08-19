package mobi.dailyevents

import groovy.util.slurpersupport.GPathResult
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.microsoft.OfficeParser
import org.apache.tika.sax.ToXMLContentHandler

class EliorRestaurantParser extends Parser {

  Parser parse(InputStream input) {
    process({
      def xmlHandler = new ToXMLContentHandler()
      new OfficeParser().parse(input, xmlHandler, new Metadata(), new ParseContext())
      def xmlString = xmlHandler.toString() //; println xmlString
      new XmlSlurper().parseText(xmlString)
    }())
    return this
  }

  private void process(GPathResult gpath) {
    def rawText  = gpath.body.div[0].div[0].p[1].toString()
    def lines    = rawText.split('\n').findAll { it.trim() }
    def sections = lines.collate(5)

    result.meta.expiration = lines[lines.size() - 1]

    5.times {
      int dayIndex = -1
      result.days.monday    << sections[it][++dayIndex]
      result.days.tuesday   << sections[it][++dayIndex]
      result.days.wednesday << sections[it][++dayIndex]
      result.days.thursday  << sections[it][++dayIndex]
      result.days.friday    << sections[it][++dayIndex]
    }
  }
}
