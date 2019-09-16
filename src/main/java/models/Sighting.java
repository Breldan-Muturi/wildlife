package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sighting {
  private int id;
  private String animal_location;
  private String ranger_name;
  private LocalDateTime sightedAt;

  public Sighting( String location, String rangerName) {
    this.animal_location = location;
    this.ranger_name = rangerName;
    this.sightedAt = LocalDateTime.now();
  }

  public LocalDateTime getSightedAt() { return sightedAt; }

  public String getAnimal_location() { return animal_location; }

  public void setAnimal_location(String animal_location) {
    this.animal_location = animal_location;
  }

  public String getRanger_name() {
    return ranger_name;
  }

  public void setRanger_name(String ranger_name) {
    this.ranger_name = ranger_name;
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
    return id == sighting.id &&
      Objects.equals(animal_location, sighting.animal_location) &&
      Objects.equals(ranger_name, sighting.ranger_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, animal_location);
  }
}
