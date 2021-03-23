package def.domain.thesaurus;

import def.exceptions.NotFoundException;
import def.models.CorpusInfo;
import def.models.PatternInfo;
import def.models.Text;
import def.repositories.CorpusRepository;
import def.repositories.PatternRepository;
import def.repositories.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CorpusDomain {
    @Autowired
    CorpusRepository corpusRepository;
    @Autowired
    PatternRepository patternRepository;
    @Autowired
    TextRepository textRepository;

    public CorpusInfo getCorpusById(int corpusId) throws NotFoundException {
        Optional<CorpusInfo> corpusInfo = corpusRepository.findById(corpusId);
        if(corpusInfo.isEmpty())
            throw new NotFoundException("Не найдено корпуса с идентификатором " + corpusId);
        return corpusInfo.get();
    }

    public List<PatternInfo> getPatternsByIds(int [] patternIds){
        List<PatternInfo> patternInfoList = new ArrayList<>();
        for (int patternId: patternIds
        ) {
            PatternInfo patternInfo = patternRepository.getOne(patternId);
            patternInfoList.add(patternInfo);
        }
        return patternInfoList;
    }

    public void saveTexts(List<Text> texts) {
        textRepository.saveAll(texts);
    }

    public void saveText(Text text){textRepository.save(text);}
}
