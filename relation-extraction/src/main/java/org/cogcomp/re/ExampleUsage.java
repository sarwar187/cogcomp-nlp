/**
 * This software is released under the University of Illinois/Research and Academic Use License. See
 * the LICENSE file in the root folder for details. Copyright (c) 2016
 *
 * Developed by: The Cognitive Computation Group University of Illinois at Urbana-Champaign
 * http://cogcomp.cs.illinois.edu/
 */
package org.cogcomp.re;

import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.annotation.TextAnnotationBuilder;
import edu.illinois.cs.cogcomp.chunker.main.ChunkerAnnotator;
import edu.illinois.cs.cogcomp.chunker.main.ChunkerConfigurator;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.*;
import edu.illinois.cs.cogcomp.core.resources.ResourceConfigurator;
import edu.illinois.cs.cogcomp.edison.utilities.WordNetManager;
import edu.illinois.cs.cogcomp.ner.ExpressiveFeatures.FlatGazetteers;
import edu.illinois.cs.cogcomp.ner.ExpressiveFeatures.GazetteersFactory;
import edu.illinois.cs.cogcomp.nlp.tokenizer.StatefulTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
import edu.illinois.cs.cogcomp.pipeline.common.Stanford331Configurator;
import edu.illinois.cs.cogcomp.pipeline.handlers.StanfordDepHandler;
import edu.illinois.cs.cogcomp.pos.POSAnnotator;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.ParserAnnotator;
import org.cogcomp.Datastore;
import org.cogcomp.md.BIOFeatureExtractor;
import org.cogcomp.md.MentionAnnotator;
//import org.cogcomp.re.LbjGen.semeval_relation_classifier;

import java.io.File;
import java.util.List;
import java.util.Properties;

import static edu.illinois.cs.cogcomp.nlp.corpusreaders.ontonotes.utils.DocumentIterator.FileKind.sense;

/**
 * Note that the functions below will not actually run.
 * They only servers a demo purpose.
 */
public class ExampleUsage {

//    public static void AnnotatorExample() {
//        String text = "He went to Chicago after his Father moved there.";
//
//        String corpus = "story";
//        String textId = "001";
//
//        // Create a TextAnnotation From Text
//        TextAnnotationBuilder stab =
//                new TokenizerTextAnnotationBuilder(new StatefulTokenizer());
//        TextAnnotation ta = stab.createTextAnnotation(corpus, textId, text);
//
//        POSAnnotator pos_annotator = new POSAnnotator();
//        ChunkerAnnotator chunker  = new ChunkerAnnotator(true);
//        chunker.initialize(new ChunkerConfigurator().getDefaultConfig());
//        Properties stanfordProps = new Properties();
//        stanfordProps.put("annotators", "pos, parse");
//        stanfordProps.put("parse.originalDependencies", true);
//        stanfordProps.put("parse.maxlen", Stanford331Configurator.STFRD_MAX_SENTENCE_LENGTH);
//        stanfordProps.put("parse.maxtime", Stanford331Configurator.STFRD_TIME_PER_SENTENCE);
//        POSTaggerAnnotator posAnnotator = new POSTaggerAnnotator("pos", stanfordProps);
//        ParserAnnotator parseAnnotator = new ParserAnnotator("parse", stanfordProps);
//        StanfordDepHandler stanfordDepHandler = new StanfordDepHandler(posAnnotator, parseAnnotator);
//        //RelationAnnotator relationAnnotator = new RelationAnnotator();
//        Relation r = new Relation();
//        try {
//            ta.addView(pos_annotator);
//            chunker.addView(ta);
//            stanfordDepHandler.addView(ta);
//            relationAnnotator.addView(ta);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        View mentionView = ta.getView(ViewNames.MENTION);
//
//        List<Constituent> predictedMentions = mentionView.getConstituents();
//        List<Relation> predictedRelations = mentionView.getRelations();
//
//        for (Relation r : predictedRelations){
//            //IOHelper.printRelation(r);
//            RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//            List featuresA = rfe.getLexicalFeaturePartA(r);
//            for (int i = 0; i < featuresA.size(); i++){
//                System.out.println(featuresA.get(i));
//            }
//        }
//    }

    public static void SemEvalAnnotate() throws AnnotatorException {
        String text = "The blast came without warning , snapping trees and engulfing Jim Scymanky and three other loggers in a suffocating wave of hot , black ash from Mount St. Helens .";
        String corpus = "semeval";
        String textId = "001";

        // Create a TextAnnotation From Text
        TextAnnotationBuilder stab =
                new TokenizerTextAnnotationBuilder(new StatefulTokenizer());
        TextAnnotation ta = stab.createTextAnnotation(corpus, textId, text);


        POSAnnotator pos_annotator = new POSAnnotator();
        ChunkerAnnotator chunker  = new ChunkerAnnotator(true);
        chunker.initialize(new ChunkerConfigurator().getDefaultConfig());
        Properties stanfordProps = new Properties();
        stanfordProps.put("annotators", "pos, parse");
        stanfordProps.put("parse.originalDependencies", true);
        stanfordProps.put("parse.maxlen", Stanford331Configurator.STFRD_MAX_SENTENCE_LENGTH);
        stanfordProps.put("parse.maxtime", Stanford331Configurator.STFRD_TIME_PER_SENTENCE);
        POSTaggerAnnotator posAnnotator = new POSTaggerAnnotator("pos", stanfordProps);
        ParserAnnotator parseAnnotator = new ParserAnnotator("parse", stanfordProps);
        StanfordDepHandler stanfordDepHandler = new StanfordDepHandler(posAnnotator, parseAnnotator);
        String modelPath = "";
        try {
            ta.addView(pos_annotator);
            chunker.addView(ta);
            stanfordDepHandler.addView(ta);
            Datastore ds = new Datastore(new ResourceConfigurator().getDefaultConfig());
            File model = ds.getDirectory("org.cogcomp.re", "SEMEVAL", 1.1, false);
            modelPath = model.getPath();
            File gazetteersResource = ds.getDirectory("org.cogcomp.gazetteers", "gazetteers", 1.3, false);
            GazetteersFactory.init(5, gazetteersResource.getPath() + File.separator + "gazetteers", true);
            WordNetManager.loadConfigAsClasspathResource(true);
            WordNetManager wordnet = WordNetManager.getInstance();
            View annotatedTokenView = new SpanLabelView("RE_ANNOTATED", ta);
            for (Constituent co : ta.getView(ViewNames.TOKENS).getConstituents()){
                Constituent c = co.cloneForNewView("RE_ANNOTATED");
                for (String s : co.getAttributeKeys()){
                    c.addAttribute(s, co.getAttribute(s));
                }
                c.addAttribute("WORDNETTAG", BIOFeatureExtractor.getWordNetTags(wordnet, c));
                c.addAttribute("WORDNETHYM", BIOFeatureExtractor.getWordNetHyms(wordnet, c));
                annotatedTokenView.addConstituent(c);
            }
            ta.addView("RE_ANNOTATED", annotatedTokenView);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        FlatGazetteers gazetteers = (FlatGazetteers)GazetteersFactory.get();
        MentionAnnotator mentionAnnotator = new MentionAnnotator();
        mentionAnnotator.addView(ta);
        Constituent source = new Constituent("first", "Mention", ta, 11, 13);
        Constituent target = new Constituent("second", "Mention", ta, 26, 29);
        source.addAttribute("GAZ", gazetteers.annotatePhrase(source));
        target.addAttribute("GAZ", gazetteers.annotatePhrase(target));
        Relation relation = new Relation("TEST", source, target, 1.0f);

        String prefix = modelPath + File.separator + "SEMEVAL" + File.separator + "SEMEVAL";

        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        Features f = new Features();
        System.out.println(f.allFeaturesString(relation));
//
//        List featuresA = rfe.getLexicalFeaturePartA(relation);
//
//        for (int i = 0; i < featuresA.size(); i++){
//            System.out.println(featuresA.get(i));
//        }
        //semeval_relation_classifier classifier = new semeval_relation_classifier(prefix + ".lc", prefix + ".lex");
        //String tag = classifier.discreteValue(relation);

        //System.out.println(tag);
    }

    public static void mentionAnnotation() throws AnnotatorException {
        String text1 = "Elsewhere , Robert Rogers had spent days evading authorities enforcing a no-trespassing zone around the mountain , which had been shaken by earthquakes for two months .";

        //String text1 = "Good afternoon, gentlemen. I am a HAL-9000 "
        //        + "computer. I was born in Urbana, Il. in 1992";

        String corpus = "2001_ODYSSEY";
        String textId = "001";

        TextAnnotationBuilder stab =
                new TokenizerTextAnnotationBuilder(new StatefulTokenizer());
        TextAnnotation ta = stab.createTextAnnotation(corpus, textId, text1);

        POSAnnotator pos_annotator = new POSAnnotator();
        ChunkerAnnotator chunker  = new ChunkerAnnotator(true);
        chunker.initialize(new ChunkerConfigurator().getDefaultConfig());
        Properties stanfordProps = new Properties();
        stanfordProps.put("annotators", "pos, parse");
        stanfordProps.put("parse.originalDependencies", true);
        stanfordProps.put("parse.maxlen", Stanford331Configurator.STFRD_MAX_SENTENCE_LENGTH);
        stanfordProps.put("parse.maxtime", Stanford331Configurator.STFRD_TIME_PER_SENTENCE);
        POSTaggerAnnotator posAnnotator = new POSTaggerAnnotator("pos", stanfordProps);
        ParserAnnotator parseAnnotator = new ParserAnnotator("parse", stanfordProps);
        StanfordDepHandler stanfordDepHandler = new StanfordDepHandler(posAnnotator, parseAnnotator);
        String modelPath = "";

        ta.addView(pos_annotator);
        chunker.addView(ta);
        stanfordDepHandler.addView(ta);

            // Create a TextAnnotation using the LBJ sentence splitter
        // and tokenizers.
        TextAnnotationBuilder tab;
        // don't split on hyphens, as NER models are trained this way
        boolean splitOnHyphens = false;
        tab = new TokenizerTextAnnotationBuilder(new StatefulTokenizer());

        //TextAnnotation ta = tab.createTextAnnotation(corpus, textId, text1);
        //POSAnnotator pos_annotator = new POSAnnotator();

        MentionAnnotator mentionAnnotator = new MentionAnnotator();
        mentionAnnotator.addView(ta);

        View mentionView = ta.getView(ViewNames.MENTION);
        List<Constituent> predictedMentions = mentionView.getConstituents();
        for (Constituent extent : predictedMentions) {
            //Get the head if needed
            Constituent head = MentionAnnotator.getHeadConstituent(extent, "MENTION_HEAD");
            System.out.println(head.getSurfaceForm());
        }
    }

    public static void main(String[] args) throws AnnotatorException {
        mentionAnnotation();
        //AnnotatorExample();
        SemEvalAnnotate();
    }
}
