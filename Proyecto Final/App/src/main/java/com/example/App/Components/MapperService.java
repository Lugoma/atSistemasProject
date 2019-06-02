package com.example.App.Components;

import java.util.List;
import java.util.Optional;

public interface MapperService <T, S>{
    /**
     * Mapper from S to T
     * @Param dao
     * @return
     */
    Optional<T> mapDaoToDto(S dao);

    /**
     * Mapper from T to S
     * @param dto
     * @return
     */
    Optional<S> mapDtoToDao(T dto);

    /**
     * Mapper from a list of S to a list of T
     * @param dao
     * @return
     */
    List<T> mapListDaoToDto(List<S> dao);

    /**
     * Mapper from a list of T to a list of S
     * @param dto
     * @return
     */
    List<S> mapListDtoToDao(List<T> dto);


}
