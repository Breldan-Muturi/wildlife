package models;

public class Animal {
  private int id;
  private int sighting_id;
  private String animal_name;

  public Animal(int sighting_id, String animal_name) {
    this.sighting_id = sighting_id;
    this.animal_name = animal_name;
  }


  public int getSighting_id() {
    return sighting_id;
  }

  public void setSighting_id(int sighting_id) {
    this.sighting_id = sighting_id;
  }

  public String getAnimal_name() {
    return animal_name;
  }

  public void setAnimal_name(String animal_name) {
    this.animal_name = animal_name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
