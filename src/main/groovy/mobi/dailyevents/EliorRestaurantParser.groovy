package mobi.dailyevents

import groovy.util.slurpersupport.GPathResult

class EliorRestaurantParser extends Parser {

  Parser parse(URL url) {
    GPathResult gpath = parseOffice(url)

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
    return this
  }
}
