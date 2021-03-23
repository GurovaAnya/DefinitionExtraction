package def.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper <E, D> implements Mapper<E, D> {

    private Class<E> entityClass;
    private Class<D> dtoClass;

    @Autowired
    ModelMapper modelMapper;

    public AbstractMapper(Class<E> entityClass, Class<D> dtoClass){
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }


    @Override
    public E toEntity(D dto) {
        return Objects.isNull(dto)
                ? null
                :modelMapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : modelMapper.map(entity, dtoClass);
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFieldsE(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFieldsD(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFieldsD(D source, E destination) {
    }

    void mapSpecificFieldsE(E source, D destination) {
    }
}
