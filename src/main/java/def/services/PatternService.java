package def.services;

import def.domain.gate.DocumentLoader;
import def.dto.PatternInfoDto;
import def.exceptions.BadRequestException;
import def.exceptions.NotFoundException;
import def.models.PatternInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatternService extends AbstractService<PatternInfo, PatternInfoDto> {

    DocumentLoader documentLoader;
    public PatternService(DocumentLoader documentLoader) {
        super(PatternInfo.class, PatternInfoDto.class);
        this.documentLoader = documentLoader;
    }

    @Override
    public List<PatternInfoDto> getAll() {
        return super.getAll();

    }

    @Override
    public PatternInfoDto getById(int id) throws NotFoundException {
        return super.getById(id);
    }

    @Override
    public PatternInfoDto post(PatternInfoDto dto) throws BadRequestException {
        return super.post(dto);
    }

    @Override
    public PatternInfoDto put(PatternInfoDto dto) throws BadRequestException, NotFoundException {
        return super.put(dto);
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        super.deleteById(id);
    }
}
