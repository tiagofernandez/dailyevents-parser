package mobi.dailyevents

class EliorRestaurantParser extends Parser {

  Parser parse(URL url) {
    String content = parseOffice(url)

    List sections = content.split('\n').flatten().findAll {
      it.trim() && !it.startsWith('Diapositive')
    }.collect { it.trim() }.collate(5)

    result.meta.lastCheck = currentDateAsString()

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
