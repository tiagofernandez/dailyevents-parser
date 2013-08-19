package mobi.dailyevents

import groovy.json.JsonOutput

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

  protected abstract Parser parse(InputStream input)

  String getResultAsJson() {
    def json = JsonOutput.toJson(result)
    JsonOutput.prettyPrint(json)
  }

  /**
   * @param args [0]: target parser, e.g. "EliorRestaurantParser"
   *             [1]: input URL, e.g. "http://restaurant.nce.amadeus.net/menu/menu.ppt"
   *             [2]: output path, e.g. "/Users/Tiago/Dropbox/Public/EliorRestaurant.json"
   */
  static void main(String... args) {
    if (args.size() != 3)
      throw new IllegalArgumentException('Target parser, input URL and output path are required')

    println "Downloading resource..."
    InputStream input  = args[1].toURL().newInputStream()

    println "Parsing fetched data..."
    String target = args[0].contains('.') ? args[0] : "mobi.dailyevents.${args[0]}"
    String result = Class.forName(target).newInstance().parse(input).resultAsJson

    println "Writing to file..."
    File output = new File(args[2])
    output.write(result, 'UTF-8')

    println "Done!"
  }
}
