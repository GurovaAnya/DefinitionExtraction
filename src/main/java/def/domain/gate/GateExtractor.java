package def.domain.gate;

import def.models.*;
import gate.*;
import gate.creole.Plugin;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class GateExtractor {

    SerialAnalyserController controller;
    Corpus corpus;
    LanguageAnalyser patternAnalyser;
    CorpusInfo corpusInfo;

    String controllerPath = "gate.creole.SerialAnalyserController";
    String engTokenizerPath = "gate.creole.tokeniser.DefaultTokeniser";
    String sentSplitterPath = "gate.creole.splitter.SentenceSplitter";
    String posTaggerPath = "gate.creole.POSTagger";
    List<PatternInfo> patternInfos;

    public GateExtractor(){

    };

    public List<Definition> extract(CorpusInfo corpusInfo, List<PatternInfo> patternInfos) throws Exception{
        this.corpusInfo = corpusInfo;
        this.patternInfos = patternInfos;

        prepareGate();
        controller.execute();
        List<Definition> definitions = getDefinitions();
        return definitions;
    }

    private List<Definition> getDefinitions() {
        List<Definition> definitions = new ArrayList<>();
        for(Document doc: corpus){
            AnnotationSet myMarkupSet = doc.getAnnotations();
            AnnotationSet definitionsAS = myMarkupSet.get("Definition");
            AnnotationSet descriptorsAS = myMarkupSet.get("Descriptor");

            for (Annotation annotation: descriptorsAS
                 ) {
                Definition definition = getInfo(annotation);
                definitions.add(definition);
            }
        }
        return definitions;
    }

    private Definition getInfo(Annotation annotationDesc){
        String descriptorText = (String)annotationDesc.getFeatures().get("descriptorString");
        Descriptor descriptor = new Descriptor(descriptorText, null,
                annotationDesc.getStartNode().getOffset(), annotationDesc.getStartNode().getOffset());


        String definitionText = (String)annotationDesc.getFeatures().get("definitionString");
        Definition definition = new Definition(definitionText, descriptor, null,
                annotationDesc.getStartNode().getOffset(), annotationDesc.getStartNode().getOffset());

        return definition;

    }

    private void prepareGate() throws Exception{
        Gate.init();
        loadCorpus();
        prepareAnniePlugin();
        loadPatterns();
        executePipeline();
    }

    private void loadCorpus() throws ResourceInstantiationException, MalformedURLException {
        corpus = Factory.newCorpus("myCorpus");
        //for (Text text: corpusInfo.getIncludedTexts()
          //   ) {
            //TODO: исправить
            //Document document = Factory.newDocument(text.getName());
            Document document = Factory.newDocument(new File("/Users/anna/Desktop/text").toURI().toURL());
            corpus.add(document);
       // }
    }

    private void loadPatterns() throws MalformedURLException, ResourceInstantiationException {
        FeatureMap patternFeature = Factory.newFeatureMap();
        //Todo: ispravit
        patternFeature.put("grammarURL", new File("/Users/anna/Desktop/101.jape").toURI().toURL());
        patternAnalyser = (LanguageAnalyser) Factory.createResource("gate.creole.Transducer", patternFeature);

        /*for (PatternInfo patternInfo: patternInfos
             ) {
            pattternFeature.put("grammarURL", new File(patternInfo.getName()).toURI().toURL());
        }*/
    }

    private void prepareAnniePlugin() throws Exception{
        Plugin anniePlugin = new Plugin.Maven("uk.ac.gate.plugins", "annie", gate.Main.version);
        Gate.getCreoleRegister().registerPlugin(anniePlugin);
    }

    private void executePipeline() throws Exception{
        ProcessingResource aEngTokenizer = (ProcessingResource) Factory.createResource(engTokenizerPath);
        ProcessingResource aEngSentSplitter = (ProcessingResource) Factory.createResource(sentSplitterPath);
        ProcessingResource tagger = (ProcessingResource) Factory.createResource(posTaggerPath);

        controller = (SerialAnalyserController) Factory.createResource(controllerPath);
        controller.add(aEngTokenizer);
        controller.add(aEngSentSplitter);
        controller.add(tagger);
        controller.add(patternAnalyser);
        controller.setCorpus(corpus);
    }
}

