package models;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Sighting {
  private String animal_location;
  private String ranger_name;
  private int animal_id;
  private int sighting_id;
  private int id;
  private DateTimeFormatter time;

  public Sighting( int sightingId, int animalId, String location, String rangerName) {
    this.sighting_id = sightingId;
    this.animal_location = location;
    this.ranger_name = rangerName;
    this.animal_id = animalId;
    this.id = 0;
  }

  public int getSighting_id() {
    return sighting_id;
  }

  public void setSighting_id(int sighting_id) {
    this.sighting_id = sighting_id;
  }

  public static Sighting setUpSighting(){
    return new Sighting( 1,1, "Zone-A", "Cliff");
  }

  public String getAnimal_location() {
    return animal_location;
  }

  public void setAnimal_location(String animal_location) {
    this.animal_location = animal_location;
  }

  public String getRanger_name() {
    return ranger_name;
  }

  public void setRanger_name(String ranger_name) {
    this.ranger_name = ranger_name;
  }

  public int getAnimal_id() {
    return animal_id;
  }

  public void setAnimal_id(int animal_id) {
    this.animal_id = animal_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sighting sighting = (Sighting) o;
    return animal_id == sighting.animal_id &&
      sighting_id == sighting.sighting_id &&
      id == sighting.id &&
      Objects.equals(animal_location, sighting.animal_location) &&
      Objects.equals(ranger_name, sighting.ranger_name) &&
      Objects.equals(time, sighting.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(animal_location, ranger_name, animal_id, sighting_id, id, time);
  }
}
