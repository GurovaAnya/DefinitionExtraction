package def.services;

import def.domain.gate.GateExtractor;
import def.domain.thesaurus.CorpusDomain;
import def.domain.thesaurus.DefinitionDomain;
import def.dto.DefinitionDto;
import def.exceptions.NotFoundException;
import def.mapper.DefinitionMapper;
import def.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtractionService {

    GateExtractor gateExtractor;
    DefinitionDomain definitionDomain;
    CorpusDomain corpusDomain;
    DefinitionMapper definitionMapper;

    @Autowired
    public ExtractionService(GateExtractor gateExtractor, DefinitionDomain definitionDomain, CorpusDomain corpusDomain, DefinitionMapper definitionMapper) {
        this.gateExtractor = gateExtractor;
        this.definitionDomain = definitionDomain;
        this.corpusDomain = corpusDomain;
        this.definitionMapper = definitionMapper;
    }

    public List<DefinitionDto> extractDefinitions(int corpusId, int[] patternIds) throws NotFoundException {
        List<Definition> def = new ArrayList<>();
        CorpusInfo corpusInfo = corpusDomain.getCorpusById(corpusId);
        List<PatternInfo> patternInfoList = corpusDomain.getPatternsByIds(patternIds);

        List<Text> nonProcessedTexts = new ArrayList<>();

        for (Text text :
                corpusInfo.getIncludedTexts()) {
            if (!text.isProcessed())
                nonProcessedTexts.add(text);
        }

        try {
            def = gateExtractor.extract(nonProcessedTexts, patternInfoList);
            for (Text text :
                    nonProcessedTexts) {
                text.isProcessed = true;
            }

            corpusDomain.saveTexts(nonProcessedTexts);
            definitionDomain.writeToThesaurus(def);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }


        return def.stream().map(d -> definitionMapper.toDto(d)).collect(Collectors.toList());
    }
}
