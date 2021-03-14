package def.services;

import def.domain.gate.GateDocumentLoader;
import def.domain.gate.GateExtractor;
import def.domain.thesaurus.CorpusDomain;
import def.domain.thesaurus.DefinitionDomain;
import def.models.CorpusInfo;
import def.models.Definition;
import def.models.PatternInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractionService {
    @Autowired
    GateExtractor gateExtractor;
    @Autowired
    GateDocumentLoader gateDocumentLoader;
    @Autowired
    DefinitionDomain definitionDomain;
    @Autowired
    CorpusDomain corpusDomain;

    public void extractDefinitions(int corpusId, int[] patternIds){
        List<Definition> def = new ArrayList<>();
        CorpusInfo corpusInfo = corpusDomain.getCorpusById(corpusId);
        List<PatternInfo> patternInfoList = corpusDomain.getPatternsByIds(patternIds);

        try {

             def = gateExtractor.extract(corpusInfo, patternInfoList);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        definitionDomain.writeToThesaurus(def);

    }


}
