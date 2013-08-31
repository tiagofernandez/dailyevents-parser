package mobi.dailyevents

class EliorRestaurantParserTest extends GroovyTestCase {

  def parse(index) {
    final url = this.class.getResource("/EliorRestaurant/menu-${index}.ppt")
    new EliorRestaurantParser().parse(url).result
  }

  void test_should_parse_week_menu_using_old_office_parser() {
    def result = parse('01')

    assert result.meta.lastCheck

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

  void test_should_parse_week_menu_using_new_office_parser() {
    def result = parse('02')

    assert result.meta.lastCheck

    assert result.days.monday.size() == 5
    assert result.days.monday[0] == 'Paupiette de bœuf maïs poivrons'
    assert result.days.monday[1] == 'Colombo de porc'
    assert result.days.monday[2] == 'Filet de colin sauce vierge'
    assert result.days.monday[3] == 'Quiche à l’emmenthal'
    assert result.days.monday[4] == 'Pizza  tomates mozzarella'

    assert result.days.tuesday.size() == 5
    assert result.days.tuesday[0] == 'Saucisse de toulouse  aux oignons'
    assert result.days.tuesday[1] == 'Hauts de cuisses de poulet'
    assert result.days.tuesday[2] == 'Filet de merlu bouton d’or'
    assert result.days.tuesday[3] == 'Curry aux légumes'
    assert result.days.tuesday[4] == 'Pizza  légumes grillés'

    assert result.days.wednesday.size() == 5
    assert result.days.wednesday[0] == 'Omelette  aux pommes de terre'
    assert result.days.wednesday[1] == 'Jambon braisé au porto'
    assert result.days.wednesday[2] == 'Filet de hoki pousses d’épinards'
    assert result.days.wednesday[3] == 'Tortellini basilic épinards'
    assert result.days.wednesday[4] == 'Pizza  pepperoni'

    assert result.days.thursday.size() == 5
    assert result.days.thursday[0] == 'Emincé de poulet au paprika'
    assert result.days.thursday[1] == 'Mignon de porc au caramel'
    assert result.days.thursday[2] == 'Filet de cabillaud  au beurre blanc'
    assert result.days.thursday[3] == 'Tarte  aux oignons reblochon'
    assert result.days.thursday[4] == 'Pizza angélica'

    assert result.days.friday.size() == 5
    assert result.days.friday[0] == 'Steak haché de veau'
    assert result.days.friday[1] == 'Spaghetti bolognaise'
    assert result.days.friday[2] == 'Filet de saumon frais'
    assert result.days.friday[3] == 'Clafoutis de légumes au chèvre'
    assert result.days.friday[4] == 'Pizza poulet curry'
  }
}
