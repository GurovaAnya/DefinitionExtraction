package def.domain.gate;

import def.models.Definition;
import def.models.Descriptor;
import def.models.PatternInfo;
import def.models.Text;
import gate.*;
import gate.creole.Plugin;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GateExtractor {

    SerialAnalyserController controller;
    Corpus corpus;
    LanguageAnalyser patternAnalyser;
    List<Text> textsToProcess;

    String controllerPath = "gate.creole.SerialAnalyserController";
    String engTokenizerPath = "gate.creole.tokeniser.DefaultTokeniser";
    String sentSplitterPath = "gate.creole.splitter.SentenceSplitter";
    String posTaggerPath = "gate.creole.POSTagger";
    List<PatternInfo> patternInfos;

    public GateExtractor(){

    };

    public List<Definition> extract(List<Text> textsToProcess, List<PatternInfo> patternInfos) throws Exception{
        this.textsToProcess = textsToProcess;
        this.patternInfos = patternInfos;

        prepareGate();
        controller.execute();
        return getDefinitions();
    }

    private List<Definition> getDefinitions() {
        List<Definition> definitions = new ArrayList<>();
        for(int i=0; i<corpus.size();i++)
        {
            AnnotationSet myMarkupSet = corpus.get(i).getAnnotations();
            AnnotationSet definitionsAS = myMarkupSet.get("Definition");

            for (Annotation annotation: definitionsAS
                 ) {
                Definition definition = getInfo(annotation, i);
                definitions.add(definition);
            }
        }
        return definitions;
    }

    private Definition getInfo(Annotation annotationDesc, int textNum){
        Text text = new Text();
        text.setId(textNum);

        String descriptorText = (String) annotationDesc.getFeatures().get("descriptorContent");
        Long descStart = (Long) annotationDesc.getFeatures().get("descStart");
        Long descEnd = (Long) annotationDesc.getFeatures().get("descEnd");

        String definitionText = (String)annotationDesc.getFeatures().get("definitionContent");
        Long defStart = (Long) annotationDesc.getFeatures().get("defStart");
        Long defEnd = (Long) annotationDesc.getFeatures().get("defEnd");

        Descriptor descriptor = new Descriptor(descriptorText, text,
                descStart, descEnd);
        Definition definition = new Definition(definitionText, descriptor, text,
                defStart, defEnd);

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
        for (Text text: textsToProcess
             ) {
            Document document = Factory.newDocument(new File(text.getName()).toURI().toURL());
            corpus.add(document);
        }
    }

    private void loadPatterns() throws MalformedURLException, ResourceInstantiationException {
        FeatureMap patternFeature = Factory.newFeatureMap();

        for (PatternInfo patternInfo: patternInfos
             ) {
            patternFeature.put("grammarURL", new File(patternInfo.getPathToFile()).toURI().toURL());
        }
        try {
            patternAnalyser = (LanguageAnalyser) Factory.createResource("gate.creole.Transducer", patternFeature);
        }
        catch(Exception ex){}
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

