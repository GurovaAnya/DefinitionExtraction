package def.domain.thesaurus;

import def.models.CorpusInfo;
import def.models.PatternInfo;
import def.repositories.CorpusRepository;
import def.repositories.PatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CorpusDomain {
    @Autowired
    CorpusRepository corpusRepository;
    @Autowired
    PatternRepository patternRepository;

    public CorpusInfo getCorpusById(int corpusId){
        CorpusInfo corpusInfo = corpusRepository.getOne(corpusId);
        return corpusInfo;
    }

    public List<PatternInfo> getPatternsByIds(int [] patternIds){
        List<PatternInfo> patternInfoList = new ArrayList<>();
        for (int patternId: patternIds
        ) {
            PatternInfo patternInfo = patternRepository.getOne(patternId);
        }
        return patternInfoList;
    }
}
