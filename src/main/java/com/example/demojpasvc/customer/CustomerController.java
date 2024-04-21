package com.example.demojpasvc.customer;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerRepository repository;

    @GetMapping
    public Page<Customer> findAll(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                  @RequestParam(name = "sortDirection", defaultValue = "ASC") Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public Page<Customer> search(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "phone", required = false) String phone,
                                 @RequestParam(name = "city", required = false) String city,
                                 @RequestParam(name = "state", required = false) String state,
                                 @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                 @RequestParam(name = "pageSize", defaultValue = "25") int pageSize) {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Specification<Customer> spec = getSpec(name, phone, city, state);
        return repository.findAll(where(spec), pageable);
    }

    private Specification<Customer> getSpec(String name, String phone, String city, String state) {
        return (root, criteriaQuery, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) predicates.add(builder.like(root.get("name"), "%" + name + "%"));
            if (phone != null) predicates.add(builder.like(root.get("phone"), "%" + phone + "%"));
            if (city != null) predicates.add(builder.like(root.get("city"), "%" + city + "%"));
            if (state != null) predicates.add(builder.like(root.get("name"), "%" + state + "%"));
            return builder.or(predicates.toArray(new Predicate[]{}));
        };
    }
}
