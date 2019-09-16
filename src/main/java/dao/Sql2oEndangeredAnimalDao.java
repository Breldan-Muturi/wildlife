package dao;

import models.EndangeredAnimal;
import org.sql2o.*;
import java.util.List;


public class Sql2oEndangeredAnimalDao implements EndangeredAnimalDao {

  private final Sql2o sql2o;

  public Sql2oEndangeredAnimalDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public List<EndangeredAnimal> getAll() {
    try (Connection con = sql2o.open()) {
      return con.createQuery("SELECT * FROM endangeredAnimals")
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  @Override
  public void add(EndangeredAnimal endangeredAnimal) {
    String sql = "INSERT INTO endangeredAnimals (animal_name, animal_health, animal_age, animal_id, sighting_id) VALUES (:animal_name, :animal_health, :animal_age, :animal_id, :sighting_id)";
    try (Connection con = sql2o.open()) {
      int id = (int) con.createQuery(sql, true)
        .bind(endangeredAnimal)
        .executeUpdate()
        .getKey();
      endangeredAnimal.setId(id);
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public EndangeredAnimal findById(int id) {
    try (Connection con = sql2o.open()) {
      return con.createQuery("SELECT * FROM endangeredAnimals WHERE id = :id")
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }

  @Override
  public void update(int id, String animal_name, String animal_health, String animal_age, int animal_id, int sighting_id) {
    String sql = "UPDATE endangeredAnimals SET animal_name = :animal_name, animal_health = :animal_health, animal_age = :animal_age, animal_id = :animal_id, sighting_id = :sighting_id WHERE id = :id";
    try (Connection con = sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("animal_name", animal_name)
        .addParameter("animal_health", animal_health)
        .addParameter("animal_age", animal_age)
        .addParameter("animal_id", animal_id)
        .addParameter("sighting_id", sighting_id)
        .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }

  }

  @Override
  public void deleteById(int id) {
    String sql = "DELETE FROM endangeredAnimals WHERE id = :id";
    try (Connection con = sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void clearAllEndangeredAnimals() {
    String sql = "DELETE FROM endangeredAnimals";
    try (Connection con = sql2o.open()) {
      con.createQuery(sql)
        .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }
}
