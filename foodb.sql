DROP TABLE IF EXISTS relation;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS ingredient;


CREATE TABLE recipe (
    recipeId       int(10) unsigned NOT NULL AUTO_INCREMENT,
    name           varchar(255) NOT NULL,
    proteins       int(4),
    fats           int(4),
    carbons        int(4),
    calories       int(4),
    isFavourite    int(1) NOT NULL,
    description    varchar(2500),
    imagepath      varchar(1000),    
    PRIMARY KEY (recipeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE ingredient (
  ingredientId int(10) unsigned NOT NULL AUTO_INCREMENT,
  name         varchar(255) NOT NULL,
  is_spice     int(1) NOT NULL,
  PRIMARY KEY (ingredientId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE relation (
  ingredient_id int(10) unsigned,
  recipe_id     int(10) unsigned,
  quantity      varchar(100) NULL,
  PRIMARY KEY (ingredient_id, recipe_id),
  FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredientId) ON UPDATE CASCADE,
  FOREIGN KEY (recipe_id) REFERENCES recipe(recipeId) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO recipe VALUES
  ( 1, 'Makaron z mozzarellą w sosie pomidorowo-śmietanowym', 28, 19, 65, 544, 0, 
    '1. Cebulę pokrój na drobną kostkę. Makaron ugotuj zgodnie z instrukcją na opakowaniu. 
     2. Rozgrzej oliwę z oliwek na patelni i podsmaż cebulę, przez około 3-4 minuty. 
     3. Następnie dodaj przecier pomidorowy, koncentrat pomidorowy i dodaj śmietanę. 
     4. Dodaj do całości ugotowany makaron i dokładnie wymieszaj. Następnie dodaj mozzarellę. Gotuj całość około 5 minut.  
     5. Na koniec dodaj listki bazylii.', 'test');
     
INSERT INTO ingredient VALUES
  ( 1, 'makaron', 0),
  ( 2, 'mozzarella light', 0),
  ( 3, 'cebula', 0),
  ( 4, 'przecier pomidorowy', 0),
  ( 5, 'koncentrat pomidorowy', 0),
  ( 6, 'śmietana 18%', 0),
  ( 7, 'oliwa z oliwek', 0),
  ( 8, 'suszona bazylia', 1),
  ( 9, 'oregano', 1),
  ( 10, 'czosnek granulowany', 1),
  ( 11, 'sól', 1),
  ( 12, 'pieprz czarny mielony', 1),
  ( 13, 'świeża bazylia', 1);

INSERT INTO relation VALUES
  ( 1, 1, '140g'),
  ( 2, 1, '125g'),
  ( 3, 1, '1x'),
  ( 4, 1, '250g'),
  ( 5, 1, '2x łyżeczka (30g)'),
  ( 6, 1, '100g'),
  ( 7, 1, '5g'),
  ( 8, 1, '1x łyżeczka'),
  ( 9, 1, '0,5 łyżeczka'),
  ( 10, 1, '3x szczypta'),
  ( 11, 1, '2x szczypta'),
  ( 12, 1, '3x szczypta'),
  ( 13, 1, '10g');
