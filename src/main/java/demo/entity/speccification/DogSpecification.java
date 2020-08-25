package demo.entity.speccification;

import demo.entity.Dog;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DogSpecification implements Specification<Dog> {

    private SearchCriteria searchCriteria;

    public DogSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Dog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (this.searchCriteria.getOperation().equals(":")) {
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue().toString() + "%");
            } else {
                return criteriaBuilder.equal(root.get(this.searchCriteria.getKey()), this.searchCriteria.getValue().toString());
            }

        }
            return null;
        }
    }
