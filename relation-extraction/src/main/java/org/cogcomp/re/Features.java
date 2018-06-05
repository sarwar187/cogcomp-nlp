package org.cogcomp.re;

import java.util.List;
//package org.cogcomp.re;
import edu.illinois.cs.cogcomp.nlp.corpusreaders.ACEReader;
import java.lang.*;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.*;
import edu.illinois.cs.cogcomp.infer.ilp.OJalgoHook;
import edu.illinois.cs.cogcomp.core.datastructures.Pair;


public class Features {

    String entity_label(Constituent c) {
        return c.getAttribute("EntityType");
    }

    //Returns the coarse-grained label of an relation
    String relation_label(Relation r) {
        return r.getAttribute("RelationType");
    }

    String fine_relation_label(Relation r) {
        return r.getAttribute("RelationSubtype");
    }

    String lexical_features(Relation r) {
        String lexicalFeaturesString = "";
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List featuresA = rfe.getLexicalFeaturePartA(r);
        for (int i = 0; i < featuresA.size(); i++) {
            lexicalFeaturesString += featuresA.get(i) + " ";
        }
        List featuresB = rfe.getLexicalFeaturePartB(r);
        for (int i = 0; i < featuresB.size(); i++) {
            lexicalFeaturesString += featuresB.get(i) + " ";
        }
        List featuresC = rfe.getLexicalFeaturePartC(r);
        for (int i = 0; i < featuresC.size(); i++) {
            lexicalFeaturesString += featuresC.get(i) + " ";
        }
        List featuresCC = rfe.getLexicalFeaturePartCC(r);
        for (int i = 0; i < featuresCC.size(); i++) {
            lexicalFeaturesString += featuresCC.get(i) + " ";
        }
        List featuresD = rfe.getLexicalFeaturePartD(r);
        for (int i = 0; i < featuresD.size(); i++) {
            lexicalFeaturesString += featuresD.get(i) + " ";
        }
        List featuresE = rfe.getLexicalFeaturePartE(r);
        for (int i = 0; i < featuresE.size(); i++) {
            lexicalFeaturesString += featuresE.get(i) + " ";
        }
        List featuresF = rfe.getLexicalFeaturePartF(r);
        for (int i = 0; i < featuresF.size(); i++) {
            lexicalFeaturesString += featuresF.get(i) + " ";
        }
        return lexicalFeaturesString;
    }

    String collocations_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getCollocationsFeature(r);
        String collocaitonsFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            collocaitonsFeaturesString += features.get(i) + " ";
        }
        return collocaitonsFeaturesString;
    }

    String structural_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getStructualFeature(r);
        String structuralFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            structuralFeaturesString += features.get(i);
        }
        return structuralFeaturesString;
    }

    String dependency_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getDependencyFeature(r);
        String dependencyFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            Pair p = (Pair) features.get(i);
            dependencyFeaturesString += (String) p.getFirst() + " " + (String) p.getSecond();
        }
        return dependencyFeaturesString;
    }

    String mention_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getMentionFeature(r);
        String mentionFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            mentionFeaturesString += features.get(i) + " ";
        }
        return mentionFeaturesString;
    }

    String shallow_parse_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getShallowParseFeature(r);
        String shallowParseFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            Pair f = (Pair) features.get(i);
            shallowParseFeaturesString += f.getFirst() + " " + (String) f.getSecond();
        }
        return shallowParseFeaturesString;
    }

    String template_features(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        List features = rfe.getTemplateFeature(r);
        String templateFeaturesString = "";
        for (int i = 0; i < features.size(); i++) {
            templateFeaturesString += "template" + " " + features.get(i);
        }
        return templateFeaturesString;
    }

    String coref_tag(Relation r) {
        RelationFeatureExtractor rfe = new RelationFeatureExtractor();
        return rfe.getCorefTag(r) + " ";
    }

    String wordNetTag(Relation r) {
        Constituent source_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getSource(), r.getSource().getTextAnnotation(), "A");
        String wordNetTagFeaturesString = "";
        for (int i = source_head.getStartSpan(); i < source_head.getEndSpan(); i++) {
            Constituent test = r.getSource().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
            String combined = test.getAttribute("WORDNETTAG");
            String[] group = combined.split(",");
            String tmp = "";
            for (int j = 0; j < group.length; j++) {
                String s = group[j];
                if (!s.equals("")) {
                    tmp += (s.split("\\."))[1];
                }
            }
            wordNetTagFeaturesString += tmp + " ";
        }
        Constituent target_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getTarget(), r.getTarget().getTextAnnotation(), "A");
        for (int i = target_head.getStartSpan(); i < target_head.getEndSpan(); i++) {
            Constituent test = r.getTarget().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
            String combined = test.getAttribute("WORDNETTAG");
            String[] group = combined.split(",");
            String tmp = "";
            for (int j = 0; j < group.length; j++) {
                String s = group[j];
                if (!s.equals("")) {
                    tmp += (s.split("\\."))[1];
                }
            }
            wordNetTagFeaturesString = tmp + " ";
        }
        return wordNetTagFeaturesString;
    }

    String wordNetHym(Relation r) {
        String wordNetHymFeatures = "";
        Constituent source_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getSource(), r.getSource().getTextAnnotation(), "A");
        for (int i = source_head.getStartSpan(); i < source_head.getEndSpan(); i++) {
            Constituent test = r.getSource().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
            String combined = test.getAttribute("WORDNETHYM");
            String[] group = combined.split(",");
            for (int j = 0; j < group.length; j++) {
                String s = group[j];
                if (!s.equals("")) {
                    return wordNetHymFeatures += s + " ";
                }
            }
        }
        Constituent target_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getTarget(), r.getTarget().getTextAnnotation(), "A");
        for (int i = target_head.getStartSpan(); i < target_head.getEndSpan(); i++) {
            Constituent test = r.getTarget().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
            String combined = test.getAttribute("WORDNETHYM");
            String[] group = combined.split(",");
            for (int j = 0; j < group.length; j++) {
                String s = group[j];
                if (!s.equals("")) {
                    return wordNetHymFeatures += s + " ";
                }
            }
        }
        return wordNetHymFeatures;
    }

    String gazetteerSource(Relation r) {
        String s = r.getSource().getAttribute("GAZ");
        String gazSourceFeatures = "";
        for (int i = 0; i < s.split(",").length; i++) {
            String feature = s.split(",")[i];
            gazSourceFeatures += "gazz-source-" + feature + " ";
        }
        return gazSourceFeatures;
    }

    String gazetteerTarget(Relation r) {
        String t = r.getTarget().getAttribute("GAZ");
        String gazTargetFeatures = "";
        for (int i = 0; i < t.split(",").length; i++) {
            String feature = t.split(",")[i];
            gazTargetFeatures += "gazz-target-" + feature + " ";
        }
        return gazTargetFeatures;
    }

    String patternFeatures(Relation r) {
        List fs = RelationFeatureExtractor.patternRecognition(r.getSource(), r.getTarget());
        String patternFeaturesString = "";
        for (int i = 0; i < fs.size(); i++) {
            patternFeaturesString += (String) fs.get(i) + " ";
        }
        return patternFeaturesString;
    }

    String gazetteerFeatures(Relation r) {
        return gazetteerSource(r) + gazetteerTarget(r) + " ";
    }

    String wordNetFeatures(Relation r) {
        return wordNetHym(r) + wordNetTag(r) + " ";
    }

    String baseFeatures(Relation r) {
        return lexical_features(r) + collocations_features(r) + structural_features(r) + mention_features(r) + template_features(r) + " ";
    }

    public String allFeaturesString(Relation r) {
        return baseFeatures(r) + wordNetFeatures(r) + gazetteerFeatures(r) + dependency_features(r) + patternFeatures(r) + shallow_parse_features(r) + " ";
    }
}
