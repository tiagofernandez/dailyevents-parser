package mobi.dailyevents

class EliorRestaurantParserTest extends GroovyTestCase {

  final input = { this.class.getResourceAsStream('/EliorRestaurant/menu.ppt') }

  void test_should_parse_week_menu() {
    def menu = new EliorRestaurantParser().parse(input()).result

    assert menu.monday.size() == 5
    assert menu.monday[0] == 'Pilons de poulet braisés'
    assert menu.monday[1] == 'Croustillant de boeuf'
    assert menu.monday[2] == 'Clafoutis de lieu et légumes'
    assert menu.monday[3] == 'Tarte fine aux poireaux'
    assert menu.monday[4] == 'Pizza  aubergines grillées'

    assert menu.tuesday.size() == 5
    assert menu.tuesday[0] == 'Merguez grillées aux herbes'
    assert menu.tuesday[1] == 'Rôti de dinde sauce tartare'
    assert menu.tuesday[2] == 'Filet de perche ciboulette'
    assert menu.tuesday[3] == 'Mille-feuille provençal'
    assert menu.tuesday[4] == 'Pizza  cabretta'

    assert menu.wednesday.size() == 5
    assert menu.wednesday[0] == 'Quiche provençale au chèvre'
    assert menu.wednesday[1] == 'Bavette d’aloyau poivre vert'
    assert menu.wednesday[2] == 'Filet de merlu pesto rouge'
    assert menu.wednesday[3] == 'Brochette d’edam et légumes'
    assert menu.wednesday[4] == 'Pizza  tomates anchois'

    assert menu.thursday.size() == 5
    assert menu.thursday[0] == 'FERIE'
    assert menu.thursday[1] == 'FERIE'
    assert menu.thursday[2] == 'FERIE'
    assert menu.thursday[3] == 'FERIE'
    assert menu.thursday[4] == 'FERIE'

    assert menu.friday.size() == 5
    assert menu.friday[0] == 'Cordon bleu napolitaine'
    assert menu.friday[1] == 'Côte de porc à l’ananas'
    assert menu.friday[2] == 'Steak de thon provençale'
    assert menu.friday[3] == 'Gnocchi aux fèves'
    assert menu.friday[4] == '-'

    shouldFail { menu.friday[4] = 'Pastel de carne' }
    shouldFail { menu.saturday = [] }
  }
}
