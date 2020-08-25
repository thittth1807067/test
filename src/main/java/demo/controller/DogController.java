package demo.controller;

import demo.entity.Dog;
import demo.entity.speccification.DogSpecification;
import demo.entity.speccification.SearchCriteria;
import demo.repository.DogRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/dogs")
public class DogController {
    @Autowired
private DogRespository dogRespository;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Dog> create(@RequestBody Dog dog) {
        Dog saveDog = dogRespository.save(dog);

        return new ResponseEntity<>(dogRespository.save(dog), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Dog>> getList() {
        return new ResponseEntity<>(dogRespository.findAll(), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/spec")
    public ResponseEntity<Iterable<Dog>> getSpecification( @RequestParam(defaultValue = "") String breedName, @RequestParam(defaultValue = "") String color,@RequestParam(defaultValue = "") int gender) {
        Specification specification  = Specification.where(null);
        if ( breedName!= null && breedName.length() >0) {
            specification = specification.and(new DogSpecification(new SearchCriteria("breedName", ":", breedName)));
        }
        if (color != null && color.length() >0){
            specification = specification.and(new DogSpecification(new SearchCriteria("color", ":", color)));
        }
        if (gender == 0 || gender == 1){
            specification = specification.and(new DogSpecification(new SearchCriteria("gender", ":", gender)));
        }

        return new ResponseEntity<>(dogRespository.findAll(specification), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Dog>  getDetail(@PathVariable int id) {
        return new ResponseEntity<>(dogRespository.findById(id).orElse(null), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Dog dog) {

        Optional<Dog> optionalDog = dogRespository.findById(id);
        if (optionalDog.isPresent()) {
            Dog existDog = optionalDog.get();
            existDog.setName(dog.getName());
            existDog.setBreedName(dog.getBreedName());
            existDog.setBirthDay(dog.getBirthDay());
            existDog.setGender(dog.getGender());
            existDog.setColor(dog.getColor());
            existDog.setStatus(dog.getStatus());
            dogRespository.save(existDog);
            return new ResponseEntity<>("success", HttpStatus.OK) ;
        }
        return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {

        Optional<Dog> optionalDog = dogRespository.findById(id);
        if (optionalDog.isPresent()) {
            dogRespository.deleteById(id);
            return new ResponseEntity<>("success", HttpStatus.OK) ;
        }
        return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);

    }
}
