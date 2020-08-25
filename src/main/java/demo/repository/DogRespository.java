package demo.repository;

import demo.entity.Dog;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DogRespository extends JpaRepository<Dog, Integer>, JpaSpecificationExecutor<Dog> {
    List<Dog> findAll(Specification specification);
}
