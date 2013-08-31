package mobi.dailyevents

import groovy.json.JsonOutput
import groovy.util.slurpersupport.GPathResult
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.microsoft.OfficeParser
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser
import org.apache.tika.sax.ToXMLContentHandler

abstract class Parser {

  final Map result = [
      meta : [:],
      days : [
        monday    : [],
        tuesday   : [],
        wednesday : [],
        thursday  : [],
        friday    : [],
        saturday  : [],
        sunday    : []
      ]
  ]

  protected abstract Parser parse(URL url)

  static GPathResult parseOffice(URL url) {
    def xmlHandler = new ToXMLContentHandler()
    try {
      // try the up to date parser
      new OOXMLParser().parse(url.newInputStream(),
        xmlHandler, new Metadata(), new ParseContext())
    }
    catch (ex) {
      // fallback to the old parser
      new OfficeParser().parse(url.newInputStream(),
        xmlHandler, new Metadata(), new ParseContext())
    }
    def xmlString = xmlHandler.toString()//; println xmlString
    new XmlSlurper().parseText(xmlString)
  }

  String getResultAsJson() {
    def json = JsonOutput.toJson(result)
    JsonOutput.prettyPrint(json)
  }

  /**
   * @param args [0]: target parser, e.g. "EliorRestaurant"
   *             [1]: input URL, e.g. "http://restaurant.nce.amadeus.net/menu/menu.ppt"
   *             [2]: output path, e.g. "/Users/Tiago/Dropbox/Public/EliorRestaurant.json"
   */
  static void main(String... args) {
    if (args.size() != 3)
      throw new IllegalArgumentException('Target parser, input URL and output path are required')

    println "Processing ${args[1]}..."
    String target = args[0].contains('.') ? args[0] : "mobi.dailyevents.${args[0]}Parser"
    String result = Class.forName(target).newInstance().parse(args[1].toURL()).resultAsJson

    println "Writing ${args[2]}..."
    File output = new File(args[2])
    output.write(result, 'UTF-8')

    println "Done!"
  }
}
