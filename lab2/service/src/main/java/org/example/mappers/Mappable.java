package org.example.mappers;


import java.util.List;

public interface Mappable<E, D> {

    D toDto(
            E model
    );

    List<D> toDto(
            List<E> model
    );

    E toModel(
            D dto
    );

    List<E> toModel(
            List<D> dto
    );

}
