package dao;

import models.Animal;
import models.EndangeredAnimal;
import models.Sighting;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface SightingDao {
  //LIST
  List<Sighting> getAll();

  //CREATE
  void add(Sighting sighting);

  //READ
  Sighting findById(int id);
  List<Animal> getAllAnimalsBySighting(int sighting_id);
  List<EndangeredAnimal> getAllEndangeredAnimalsBySighting(int sighting_id);

  //UPDATE
  void update(String animal_location, String ranger_name, int animal_id, int id, DateTimeFormatter time);

  //DELETE
  void deleteById(int id);
  void clearAllSightings();
}
