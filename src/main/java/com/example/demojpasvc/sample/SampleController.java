package com.example.demojpasvc.sample;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/samples")
@CrossOrigin
public class SampleController {

    private final SampleRepository repository;

    @GetMapping()
    public Page<Sample> findAll(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                @RequestParam(name = "sortDirection", defaultValue = "ASC") Direction direction) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy)));
    }

}
