package def.services;

import def.dto.AbstractDto;
import def.exceptions.BadRequestException;
import def.exceptions.NotFoundException;
import def.mapper.AbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractService<Entity, Dto extends AbstractDto> {
    
    private Class<Entity> entityClass;
    private Class<Dto> dtoClass;
    protected AbstractMapper<Entity, Dto> mapper;
    protected JpaRepository<Entity, Integer> repository;

    public AbstractService(Class<Entity> entityClass, Class<Dto> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public List<Dto> getAll() {
        List<Entity> entities = repository.findAll();
        return entities.stream().map(c -> mapper.toDto(c)).collect(Collectors.toList());
    }

    public Dto getById(int id) throws NotFoundException {
        Optional<Entity> entity = repository.findById(id);
        if (entity.isEmpty())
            throw new NotFoundException("Не найдено сущности с идентификатором " + id);
        return mapper.toDto(entity.get());
    }

    public Dto post(Dto dto) throws BadRequestException {
        Entity entity= mapper.toEntity(dto);
        try{
            entity= repository.save(entity);}
        catch (DataIntegrityViolationException ex){
            throw new BadRequestException("Невозможно создать сущность с данными ссылками");
        }
        return mapper.toDto(entity);
    }

    public Dto put(Dto dto) throws BadRequestException, NotFoundException {
        if (dto.getId() == null)
            throw new BadRequestException("Не указан идентификатор");
        Entity entity = mapper.toEntity(dto);
        if (repository.findById(dto.getId()).isEmpty())
            throw new NotFoundException("Не найдено сущности с идентификатором " + dto.getId());
        Entity result = repository.save(entity);
        return mapper.toDto(entity);
    }

    public void deleteById(int id) throws NotFoundException{
        try {
            repository.deleteById(id);
        }
        catch (Exception ex){
            throw new NotFoundException("Не найдено сущности с идентификатором " + id);
        }
    }
    
}
