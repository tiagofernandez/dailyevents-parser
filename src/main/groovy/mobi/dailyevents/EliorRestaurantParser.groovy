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

  private void process(GPathResult result) {
    def rawText  = result.body.div[0].div[0].p[1].toString()
    def lines    = rawText.split('\n').findAll { it.trim() }
    def sections = lines.collate(5)

    5.times {
      int dayIndex = -1
      add('monday', sections[it][++dayIndex])
      add('tuesday', sections[it][++dayIndex])
      add('wednesday', sections[it][++dayIndex])
      add('thursday', sections[it][++dayIndex])
      add('friday', sections[it][++dayIndex])
    }
  }
}
