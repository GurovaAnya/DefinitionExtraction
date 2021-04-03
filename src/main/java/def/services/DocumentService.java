package def.services;

import def.domain.gate.DocumentLoader;
import def.domain.thesaurus.CorpusDomain;
import def.exceptions.NotFoundException;
import def.models.CorpusInfo;
import def.models.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class DocumentService {
    @Autowired
    DocumentLoader documentLoader;

    @Autowired
    CorpusDomain corpusDomain;

    public String loadDocument(Integer corpusId, File file) throws NotFoundException {
        CorpusInfo corpusInfo = corpusDomain.getCorpusById(corpusId);
        String path =  documentLoader.uploadFile(file);
        Text text = new Text(path, null, null, corpusInfo);
        corpusDomain.saveText(text);
        return path;
    }

}
