import dao.Sql2oAnimalDao;
import dao.Sql2oEndangeredAnimalDao;
import dao.Sql2oSightingDao;
import models.Animal;
import models.EndangeredAnimal;
import models.Sighting;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.post;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String connectionString = "jdbc:postgresql://localhost:5432/todolist";      //connect to todolist, not todolist_test!
    Sql2o sql2o = new Sql2o(connectionString, "turi", "Sunrise@1997");
    Sql2oEndangeredAnimalDao endangeredAnimalDao = new Sql2oEndangeredAnimalDao(sql2o);
    Sql2oAnimalDao animalDao = new Sql2oAnimalDao(sql2o);
    Sql2oSightingDao sightingDao = new Sql2oSightingDao(sql2o);
    Map<String,Object> model = new HashMap<>();

//get: show all endangered animals, animals and sightings
    get("/", (req, res) -> {
      List<Sighting> allSightings = sightingDao.getAll();
      model.put("sightings", allSightings);
      List<Animal> animals = animalDao.getAll();
      model.put("animals", animals);
      List<EndangeredAnimal> allEndangeredAnimals = endangeredAnimalDao.getAll();
      model.put("endangeredAnimals", allEndangeredAnimals);
      return new ModelAndView(model, "index.hbs");
    }, new HandlebarsTemplateEngine());

    //    get: add new sightings
    get("/sightings/new",(req,res)->{
      List<Sighting> sightings = sightingDao.getAll();
      model.put("sightings", sightings);
      return new ModelAndView ( model, "sighting-form.hbs");
    },new HandlebarsTemplateEngine());

    //    post: process a form to create a new sighting
    post("/sightings", (req, res) -> { //new
      String animal_location = req.queryParams("animal_location");
      String ranger_name = req.queryParams("ranger_name");
      Sighting newSighting = new Sighting(animal_location, ranger_name);
      sightingDao.add(newSighting);
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get: delete all sightings and all animals and all endangered animals
    get("/sightings/delete", (req, res) -> {
      sightingDao.clearAllSightings();
      animalDao.clearAllAnimals();
      endangeredAnimalDao.clearAllEndangeredAnimals();
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get: delete all heroes
    get("/endangeredAnimals/delete", (req, res) -> {
      endangeredAnimalDao.clearAllEndangeredAnimals();
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get a specific sighting (and the animals and endangered animals it contains)
    get("/sightings/:id", (req, res) -> {
      int idOfSightingToFind = Integer.parseInt(req.params("id")); //new
      Sighting foundSighting = sightingDao.findById(idOfSightingToFind);
      model.put("sighting", foundSighting);
      List<Animal> allAnimalsBySighting = sightingDao.getAllAnimalsBySighting(idOfSightingToFind);
      model.put("animals", allAnimalsBySighting);
      model.put("sightings", sightingDao.getAll()); //refresh list of links for navbar
      List<EndangeredAnimal> allEndangeredAnimalsBySighting = sightingDao.getAllEndangeredAnimalsBySighting(idOfSightingToFind);
      model.put("endangeredAnimals", allEndangeredAnimalsBySighting);
      model.put("sightings", sightingDao.getAll()); //refresh list of links for navbar
      return new ModelAndView(model, "sighting-detail.hbs"); //new
    }, new HandlebarsTemplateEngine());

    //get: show a form to update a sighting
    get("/sightings/:id/edit", (req, res) -> {
      model.put("editSighting", true);
      Sighting sighting = sightingDao.findById(Integer.parseInt(req.params("id")));
      model.put("sighting", sighting);
      model.put("sightings", sightingDao.getAll()); //refresh list of links for navbar
      return new ModelAndView(model, "sighting-form.hbs");
    }, new HandlebarsTemplateEngine());

    //post: process a form to update a sighting
    post("/sightings/:id", (req, res) -> {
      int idOfSightingToEdit = Integer.parseInt(req.params("id"));
      String animal_location = req.queryParams("animal_location");
      String ranger_name = req.queryParams("ranger_name");
      sightingDao.update(idOfSightingToEdit, animal_location, ranger_name);
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get: delete an individual hero
    get("/sightings/:sighting_id/heroes/:hero_id/delete", (req, res) -> {
      int idOfHeroToDelete = Integer.parseInt(req.params("hero_id"));
      heroDao.deleteById(idOfHeroToDelete);
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get: delete an individual hero
    get("/sightings/:sighting_id/heroes/:hero_id/delete", (req, res) -> {
      int idOfHeroToDelete = Integer.parseInt(req.params("hero_id"));
      heroDao.deleteById(idOfHeroToDelete);
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //    get: add new heroes
    get("/heroes/new",(req,res)->{
      List<Sighting> sightings = sightingDao.getAll();
      model.put("sightings", sightings);
      return new ModelAndView ( model, "hero-form.hbs");
    },new HandlebarsTemplateEngine());

    //    post: process new hero form
    post("/heroes", (req, res) -> { //new
      String name = req.queryParams("name");
      int age = Integer.parseInt(req.queryParams("age"));
      String origin_story = req.queryParams("origin_story");
      String powers = req.queryParams("powers");
      String weaknesses = req.queryParams("weaknesses");
      int sighting_id = Integer.parseInt(req.queryParams("sighting_id"));
      Hero newHero = new Hero(name,age, origin_story, powers,weaknesses,sighting_id);
      heroDao.add(newHero);
      List<Sighting> allSightings = sightingDao.getAll();
      model.put("sightings", allSightings);
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());

    //get: show an individual hero that is nested in a sighting
    get("/sightings/:sighting_id/heroes/:hero_id", (req, res) -> {
      int idOfHeroToFind = Integer.parseInt(req.params("hero_id")); //pull id - must match route segment
      Hero foundHero = heroDao.findById(idOfHeroToFind); //use it to find hero
      int idOfSightingToFind = Integer.parseInt(req.params("sighting_id"));
      Sighting foundSighting = sightingDao.findById(idOfSightingToFind);
      model.put("sighting", foundSighting);
      model.put("hero", foundHero); //add it to model for template to display
      model.put("sightings", sightingDao.getAll()); //refresh list of links for navbar
      return new ModelAndView(model, "hero-detail.hbs"); //individual hero page.
    }, new HandlebarsTemplateEngine());

    //get: show a form to update a hero
    get("/heroes/:id/edit", (req, res) -> {
      List<Sighting> allSightings = sightingDao.getAll();
      model.put("sightings", allSightings);
      Hero hero = heroDao.findById(Integer.parseInt(req.params("id")));
      model.put("hero", hero);
      model.put("editHero", true);
      return new ModelAndView(model, "hero-form.hbs");
    }, new HandlebarsTemplateEngine());

    //hero: process a form to update a hero
    post("/heroes/:id", (req, res) -> { //URL to update hero on POST route
      int heroToEditId = Integer.parseInt(req.params("id"));
      String newName = req.queryParams("name");
      int newAge = Integer.parseInt(req.queryParams("age"));
      String newOrigin_story = req.queryParams("origin_story");
      String newPowers = req.queryParams("powers");
      String newWeaknesses = req.queryParams("weaknesses");
      int newSighting_id = Integer.parseInt(req.queryParams("sighting_id"));
      heroDao.update(heroToEditId, newName, newAge, newOrigin_story, newPowers, newWeaknesses, newSighting_id);  // remember the hardcoded sightingId we placed? See what we've done to/with it?
      res.redirect("/");
      return null;
    }, new HandlebarsTemplateEngine());


  }
}
