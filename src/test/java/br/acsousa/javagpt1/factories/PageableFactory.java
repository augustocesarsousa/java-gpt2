package br.acsousa.javagpt1.factories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFactory {

    public static Pageable createPageable() {
        return PageRequest.of(0, 10, Sort.Direction.valueOf("id"));
    }
}
