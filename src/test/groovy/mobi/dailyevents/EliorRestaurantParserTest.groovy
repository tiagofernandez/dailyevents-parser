package mobi.dailyevents

class EliorRestaurantParserTest extends GroovyTestCase {

  final input = { this.class.getResourceAsStream('/EliorRestaurant/menu.ppt') }

  void test_should_parse_week_menu() {
    def result = new EliorRestaurantParser().parse(input()).result

    assert result.meta.expiration == '16/08'

    assert result.days.monday.size() == 5
    assert result.days.monday[0] == 'Pilons de poulet braisés'
    assert result.days.monday[1] == 'Croustillant de boeuf'
    assert result.days.monday[2] == 'Clafoutis de lieu et légumes'
    assert result.days.monday[3] == 'Tarte fine aux poireaux'
    assert result.days.monday[4] == 'Pizza  aubergines grillées'

    assert result.days.tuesday.size() == 5
    assert result.days.tuesday[0] == 'Merguez grillées aux herbes'
    assert result.days.tuesday[1] == 'Rôti de dinde sauce tartare'
    assert result.days.tuesday[2] == 'Filet de perche ciboulette'
    assert result.days.tuesday[3] == 'Mille-feuille provençal'
    assert result.days.tuesday[4] == 'Pizza  cabretta'

    assert result.days.wednesday.size() == 5
    assert result.days.wednesday[0] == 'Quiche provençale au chèvre'
    assert result.days.wednesday[1] == 'Bavette d’aloyau poivre vert'
    assert result.days.wednesday[2] == 'Filet de merlu pesto rouge'
    assert result.days.wednesday[3] == 'Brochette d’edam et légumes'
    assert result.days.wednesday[4] == 'Pizza  tomates anchois'

    assert result.days.thursday.size() == 5
    assert result.days.thursday[0] == 'FERIE'
    assert result.days.thursday[1] == 'FERIE'
    assert result.days.thursday[2] == 'FERIE'
    assert result.days.thursday[3] == 'FERIE'
    assert result.days.thursday[4] == 'FERIE'

    assert result.days.friday.size() == 5
    assert result.days.friday[0] == 'Cordon bleu napolitaine'
    assert result.days.friday[1] == 'Côte de porc à l’ananas'
    assert result.days.friday[2] == 'Steak de thon provençale'
    assert result.days.friday[3] == 'Gnocchi aux fèves'
    assert result.days.friday[4] == '-'
  }
}
