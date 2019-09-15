package dao;

import models.Animal;
import models.EndangeredAnimal;

import java.util.List;

public interface AnimalDao {
  //LIST
  List<Animal> getAll();

  //CREATE
  void add(Animal animal);

  //READ
  Animal findById(int id);
  List<EndangeredAnimal> getAllEndangeredAnimalsByAnimal(int animal_id);

  //UPDATE
  void update(int animal_id, String animal_name, String animal_health, String animal_age, int id);

  //DELETE
  void deleteById(int id);
  void clearAllAnimals();
}
