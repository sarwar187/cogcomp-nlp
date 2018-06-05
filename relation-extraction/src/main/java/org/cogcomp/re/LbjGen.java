//package org.cogcomp.re;
//import edu.illinois.cs.cogcomp.nlp.corpusreaders.ACEReader;
//import java.util.List;
//import java.lang.*;
//import edu.illinois.cs.cogcomp.core.datastructures.textannotation.*;
//import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
//import edu.illinois.cs.cogcomp.infer.ilp.OJalgoHook;
//import edu.illinois.cs.cogcomp.core.datastructures.Pair;
//import org.cogcomp.md.BIOFeatureExtractor;
//import org.cogcomp.re.RelationFeatureExtractor;
//
//discrete entity_label(Constituent c) <- {
//    return c.getAttribute("EntityType");
//}
//
////Returns the coarse-grained label of an relation
//discrete relation_label(Relation r) <- {
//    return r.getAttribute("RelationType");
//}
//
//discrete fine_relation_label(Relation r) <- {
//    return r.getAttribute("RelationSubtype");
//}
//
//discrete% lexical_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List featuresA = rfe.getLexicalFeaturePartA(r);
//    for (int i = 0; i < featuresA.size(); i++){
//        sense "A" : featuresA.get(i);
//    }
//    List featuresB = rfe.getLexicalFeaturePartB(r);
//    for (int i = 0; i < featuresB.size(); i++){
//        sense "B" : featuresB.get(i);
//    }
//    List featuresC = rfe.getLexicalFeaturePartC(r);
//    for (int i = 0; i < featuresC.size(); i++){
//        sense "C" : featuresC.get(i);
//    }
//    List featuresCC = rfe.getLexicalFeaturePartCC(r);
//    for (int i = 0; i < featuresCC.size(); i++){
//        sense "CC" : featuresCC.get(i);
//    }
//    List featuresD = rfe.getLexicalFeaturePartD(r);
//    for (int i = 0; i < featuresD.size(); i++){
//        sense "D" : featuresD.get(i);
//    }
//    List featuresE = rfe.getLexicalFeaturePartE(r);
//    for (int i = 0; i < featuresE.size(); i++){
//        sense "E" : featuresE.get(i);
//    }
//    List featuresF = rfe.getLexicalFeaturePartF(r);
//    for (int i = 0; i < featuresF.size(); i++){
//        sense "F" : featuresF.get(i);
//    }
//}
//
//discrete% collocations_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getCollocationsFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        sense features.get(i);
//    }
//}
//
//discrete% structural_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getStructualFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        sense features.get(i);
//    }
//}
//
//discrete% dependency_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getDependencyFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        Pair p = (Pair)features.get(i);
//        sense (String)p.getFirst() : (String)p.getSecond();
//    }
//}
//
//discrete% mention_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getMentionFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        sense features.get(i);
//    }
//}
//
//discrete% shallow_parse_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getShallowParseFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        Pair f = (Pair)features.get(i);
//        sense (String)f.getFirst() : (String)f.getSecond();
//    }
//}
//
//discrete% template_features(Relation r) <- {
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    List features = rfe.getTemplateFeature(r);
//    for (int i = 0; i < features.size(); i++){
//        sense "template" : features.get(i);
//    }
//}
//
//discrete{"TRUE", "FALSE"} coref_tag(Relation r) <-{
//    RelationFeatureExtractor rfe = new RelationFeatureExtractor();
//    return rfe.getCorefTag(r);
//}
//
//discrete% wordNetTag (Relation r) <- {
//    Constituent source_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getSource(), r.getSource().getTextAnnotation(), "A");
//    for (int i = source_head.getStartSpan(); i < source_head.getEndSpan(); i++){
//        Constituent test = r.getSource().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
//        String combined = test.getAttribute("WORDNETTAG");
//        String[] group = combined.split(",");
//            String tmp = "";
//            for (int j = 0; j < group.length; j++){
//                String s = group[j];
//                if (!s.equals("")){
//                    tmp +=  (s.split("\\."))[1];
//                }
//            }
//            sense tmp;
//    }
//    Constituent target_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getTarget(), r.getTarget().getTextAnnotation(), "A");
//    for (int i = target_head.getStartSpan(); i < target_head.getEndSpan(); i++){
//        Constituent test = r.getTarget().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
//        String combined = test.getAttribute("WORDNETTAG");
//        String[] group = combined.split(",");
//            String tmp = "";
//            for (int j = 0; j < group.length; j++){
//                String s = group[j];
//                if (!s.equals("")){
//                    tmp +=  (s.split("\\."))[1];
//                }
//            }
//            sense tmp;
//        }
//}
//
//discrete% wordNetHym (Relation r) <- {
//    Constituent source_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getSource(), r.getSource().getTextAnnotation(), "A");
//    for (int i = source_head.getStartSpan(); i < source_head.getEndSpan(); i++){
//        Constituent test = r.getSource().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
//        String combined = test.getAttribute("WORDNETHYM");
//        String[] group = combined.split(",");
//        for (int j = 0; j < group.length; j++){
//            String s = group[j];
//            if (!s.equals("")){
//                sense s;
//            }
//        }
//    }
//    Constituent target_head = RelationFeatureExtractor.getEntityHeadForConstituent(r.getTarget(), r.getTarget().getTextAnnotation(), "A");
//    for (int i = target_head.getStartSpan(); i < target_head.getEndSpan(); i++){
//        Constituent test = r.getTarget().getTextAnnotation().getView("RE_ANNOTATED").getConstituentsCoveringToken(i).get(0);
//        String combined = test.getAttribute("WORDNETHYM");
//        String[] group = combined.split(",");
//        for (int j = 0; j < group.length; j++){
//            String s= group[j];
//            if (!s.equals("")){
//                sense s;
//            }
//        }
//    }
//}
//
//discrete% gazetteerSource (Relation r) <- {
//    String s = r.getSource().getAttribute("GAZ");
//    for (int i = 0; i < s.split(",").length; i++){
//        String feature = s.split(",")[i];
//        sense "gazz-source-" : feature;
//    }
//}
//
//discrete% gazetteerTarget (Relation r) <- {
//    String t = r.getTarget().getAttribute("GAZ");
//    for (int i = 0; i < t.split(",").length; i++){
//        String feature = t.split(",")[i];
//        sense "gazz-target-" : feature;
//    }
//}
//
//discrete% patternFeatures (Relation r) <- {
//    List fs = RelationFeatureExtractor.patternRecognition(r.getSource(), r.getTarget());
//    for (int i = 0; i < fs.size(); i++){
//        sense (String)fs.get(i);
//    }
//}
//
//discrete% gazetteerFeatures (Relation r) <-
//    gazetteerSource, gazetteerTarget
//
//discrete% wordNetFeatures (Relation r) <-
//    wordNetHym, wordNetTag
//
//discrete% baseFeatures (Relation r) <-
//    lexical_features, collocations_features, structural_features, mention_features, template_features
//
//discrete relation_classifier(Relation r) <-
//learn fine_relation_label
//    using baseFeatures, wordNetFeatures, gazetteerFeatures, dependency_features, patternFeatures, shallow_parse_features
//    with SupportVectorMachine{
//
//    }
//end
//
//discrete semeval_relation_classifier (Relation r) <-
//learn fine_relation_label
//    using lexical_features, collocations_features, wordNetFeatures, gazetteerFeatures, dependency_features, shallow_parse_features, template_features, mention_features
//    with SupportVectorMachine{
//    }
//end
//
//constraint coref_constraint(Relation r)
//{
//    relation_classifier(r)!:"NOT_RELATED" => coref_tag(r)::"FALSE";
//}
//constraint Located_ET (Relation r)
//{
//    relation_classifier(r)::"Located" => entity_label(r.getSource())::"PER" (entity_label(r.getTarget())::"FAC" entity_label(r.getTarget())::"LOC" entity_label(r.getTarget())::"GPE");
//}
//constraint Located_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Located_OP" => entity_label(r.getTarget())::"PER" (entity_label(r.getSource())::"FAC" entity_label(r.getSource())::"LOC" entity_label(r.getSource())::"GPE");
//}
//constraint Artifact_ET (Relation r)
//{
//    relation_classifier(r)::"Artifact" => (entity_label(r.getSource())::"VEH" entity_label(r.getTarget())::"VEH") (entity_label(r.getSource())::"WEA" entity_label(r.getTarget())::"WEA");
//}
//constraint Artifact_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Artifact_OP" => (entity_label(r.getSource())::"VEH" entity_label(r.getTarget())::"VEH") (entity_label(r.getSource())::"WEA" entity_label(r.getTarget())::"WEA");
//}
//constraint Business_ET (Relation r)
//{
//    relation_classifier(r)::"Business" => entity_label(r.getSource())::"PER" entity_label(r.getTarget())::"PER";
//}
//constraint Business_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Business_OP" => entity_label(r.getSource())::"PER" entity_label(r.getTarget())::"PER";
//}
//constraint CRRE_ET (Relation r)
//{
//    relation_classifier(r)::"Citizen-Resident-Religion-Ethnicity" => entity_label(r.getSource())::"PER" (entity_label(r.getTarget())::"LOC" entity_label(r.getTarget())::"GPE" entity_label(r.getTarget())::"ORG" entity_label(r.getTarget())::"PER");
//}
//constraint CRRE_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Citizen-Resident-Religion-Ethnicity_OP" => entity_label(r.getTarget())::"PER" (entity_label(r.getSource())::"LOC" entity_label(r.getSource())::"GPE" entity_label(r.getSource())::"ORG" entity_label(r.getSource())::"PER");
//}
//constraint Employment_ET (Relation r)
//{
//    relation_classifier(r)::"Employment" => entity_label(r.getSource())::"PER"
//                                           (entity_label(r.getTarget())::"ORG"
//                                            entity_label(r.getTarget())::"GPE");
//}
//constraint Employment_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Employment_OP" => entity_label(r.getTarget())::"PER" /\
//                                           (entity_label(r.getSource())::"ORG" \/
//                                            entity_label(r.getSource())::"GPE");
//}
//constraint Family_ET (Relation r)
//{
//    relation_classifier(r)::"Family" => entity_label(r.getSource())::"PER"
//                                        entity_label(r.getTarget())::"PER";
//}
//constraint Family_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Family_OP" => entity_label(r.getSource())::"PER"
//                                        entity_label(r.getTarget())::"PER";
//}
//constraint Founder_ET (Relation r)
//{
//    relation_classifier(r)::"Founder" => (entity_label(r.getSource())::"PER"
//                                          entity_label(r.getSource())::"ORG")
//                                         (entity_label(r.getTarget())::"ORG"
//                                          entity_label(r.getTarget())::"GPE");
//}
//constraint Founder_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Founder_OP" => (entity_label(r.getTarget())::"PER"
//                                          entity_label(r.getTarget())::"ORG")
//                                         (entity_label(r.getSource())::"ORG"
//                                          entity_label(r.getSource())::"GPE");
//}
//constraint Geographical_ET (Relation r)
//{
//    relation_classifier(r)::"Geographical" => (entity_label(r.getSource())::"FAC"
//                                               entity_label(r.getSource())::"LOC"
//                                               entity_label(r.getSource())::"GPE")
//                                              (entity_label(r.getTarget())::"FAC"
//                                               entity_label(r.getTarget())::"LOC"
//                                               entity_label(r.getTarget())::"GPE");
//}
//constraint Geographical_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Geographical_OP" => (entity_label(r.getSource())::"FAC"
//                                               entity_label(r.getSource())::"LOC"
//                                               entity_label(r.getSource())::"GPE")
//                                              (entity_label(r.getTarget())::"FAC"
//                                               entity_label(r.getTarget())::"LOC"
//                                               entity_label(r.getTarget())::"GPE");
//}
//constraint IS_ET (Relation r)
//{
//    relation_classifier(r)::"Investor-Shareholder" => (entity_label(r.getSource())::"PER"
//                                                       entity_label(r.getSource())::"ORG"
//                                                       entity_label(r.getSource())::"GPE")
//                                                      (entity_label(r.getTarget())::"ORG"
//                                                       entity_label(r.getTarget())::"GPE");
//}
//constraint IS_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Investor-Shareholder_OP" => (entity_label(r.getTarget())::"PER"
//                                                       entity_label(r.getTarget())::"ORG"
//                                                       entity_label(r.getTarget())::"GPE")
//                                                      (entity_label(r.getSource())::"ORG"
//                                                       entity_label(r.getSource())::"GPE");
//}
//constraint LP_ET (Relation r)
//{
//    relation_classifier(r)::"Lasting-Personal" => entity_label(r.getSource())::"PER"
//                                                  entity_label(r.getTarget())::"PER";
//}
//constraint LP_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Lasting-Personal_OP" => entity_label(r.getSource())::"PER"
//                                                  entity_label(r.getTarget())::"PER";
//}
//constraint Membership_ET (Relation r)
//{
//    relation_classifier(r)::"Membership" => (entity_label(r.getSource())::"PER"
//                                             entity_label(r.getSource())::"ORG"
//                                             entity_label(r.getSource())::"GPE")
//                                             entity_label(r.getTarget())::"ORG";
//}
//constraint Membership_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Membership_OP" => (entity_label(r.getTarget())::"PER"
//                                             entity_label(r.getTarget())::"ORG"
//                                             entity_label(r.getTarget())::"GPE")
//                                             entity_label(r.getSource())::"ORG";
//}
//constraint Near_ET (Relation r)
//{
//    relation_classifier(r)::"Near" => (entity_label(r.getSource())::"PER"
//                                       entity_label(r.getSource())::"FAC"
//                                       entity_label(r.getSource())::"GPE"
//                                       entity_label(r.getSource())::"LOC")
//                                      (entity_label(r.getTarget())::"FAC"
//                                       entity_label(r.getTarget())::"GPE"
//                                       entity_label(r.getTarget())::"LOC"
//                                       entity_label(r.getTarget())::"PER");
//}
//constraint Near_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Near_OP" => (entity_label(r.getTarget())::"PER"
//                                       entity_label(r.getTarget())::"FAC"
//                                       entity_label(r.getTarget())::"GPE"
//                                       entity_label(r.getTarget())::"LOC")
//                                      (entity_label(r.getSource())::"FAC"
//                                       entity_label(r.getSource())::"GPE"
//                                       entity_label(r.getSource())::"LOC");
//}
//constraint OL_ET (Relation r)
//{
//    relation_classifier(r)::"Org-Location" => entity_label(r.getSource())::"ORG"
//                                             (entity_label(r.getTarget())::"LOC"
//                                              entity_label(r.getTarget())::"GPE");
//}
//constraint OL_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Org-Location_OP" => entity_label(r.getTarget())::"ORG"
//                                             (entity_label(r.getSource())::"LOC"
//                                              entity_label(r.getSource())::"GPE");
//}
//constraint Ownership_ET (Relation r)
//{
//    relation_classifier(r)::"Ownership" => entity_label(r.getSource())::"PER"
//                                           entity_label(r.getTarget())::"ORG";
//}
//constraint Ownership_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Ownership_OP" => entity_label(r.getSource())::"ORG"
//                                              entity_label(r.getTarget())::"PER";
//}
//constraint SA_ET (Relation r)
//{
//    relation_classifier(r)::"Sports-Affiliation" => entity_label(r.getSource())::"PER"
//                                                    entity_label(r.getTarget())::"ORG";
//}
//constraint SA_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Sports-Affiliation_OP" => entity_label(r.getSource())::"ORG"
//                                                       entity_label(r.getTarget())::"PER";
//}
//constraint SAL_ET (Relation r)
//{
//    relation_classifier(r)::"Student-Alum" => entity_label(r.getSource())::"PER"
//                                              entity_label(r.getTarget())::"ORG";
//}
//constraint SAL_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Student-Alum_OP" => entity_label(r.getSource())::"PER"
//                                              entity_label(r.getTarget())::"ORG";
//}
//constraint Subsidiary_ET (Relation r)
//{
//    relation_classifier(r)::"Subsidiary" => entity_label(r.getSource())::"ORG"
//                                           (entity_label(r.getTarget())::"ORG"
//                                            entity_label(r.getTarget())::"GPE");
//}
//constraint Subsidiary_OP_ET (Relation r)
//{
//    relation_classifier(r)::"Subsidiary_OP" => entity_label(r.getTarget())::"ORG"
//                                           (entity_label(r.getSource())::"ORG"
//                                            entity_label(r.getSource())::"GPE");
//}
//constraint UOIM_ET (Relation r)
//{
//    relation_classifier(r)::"User-Owner-Inventor-Manufacturer" => (entity_label(r.getSource())::"PER"
//                                                                   entity_label(r.getSource())::"ORG"
//                                                                   entity_label(r.getSource())::"GPE")
//                                                                  (entity_label(r.getTarget())::"WEA"
//                                                                   entity_label(r.getTarget())::"VEH"
//                                                                   entity_label(r.getTarget())::"FAC");
//}
//constraint UOIM_OP_ET (Relation r)
//{
//    relation_classifier(r)::"User-Owner-Inventor-Manufacturer_OP" => (entity_label(r.getTarget())::"PER"
//                                                                   entity_label(r.getTarget())::"ORG"
//                                                                   entity_label(r.getTarget())::"GPE")
//                                                                  (entity_label(r.getSource())::"WEA"
//                                                                   entity_label(r.getSource())::"VEH"
//                                                                   entity_label(r.getSource())::"FAC");
//}
//
//inference constrained_RE head Relation r {
//    Relation r {return r;}
//    subjectto {@Located_ET(r)  @Located_OP_ET(r) /\ @Artifact_ET(r) /\ @Artifact_OP_ET(r) /\
//               @Business_ET(r) /\ @CRRE_ET(r) /\ @CRRE_OP_ET(r) /\
//               @Employment_ET(r) /\ @Employment_OP_ET(r) /\ @Family_ET(r) /\
//               @Founder_ET(r) /\ @Founder_OP_ET(r) /\ @Geographical_ET(r) /\ @Geographical_OP_ET(r) /\
//               @IS_ET(r) /\ @IS_OP_ET(r) /\ @LP_ET(r) /\ @Membership_ET(r) /\ @Membership_OP_ET(r) /\
//               @Near_ET(r) /\ @OL_ET(r) /\ @OL_OP_ET(r) /\ @Ownership_ET(r) /\ @Ownership_OP_ET(r) /\
//               @SA_ET(r) /\ @SA_OP_ET(r) /\ @SAL_ET(r) /\ @SAL_OP_ET(r) /\ @Subsidiary_ET(r) /\ @Subsidiary_OP_ET(r) /\
//               @UOIM_ET(r) /\ @UOIM_OP_ET(r);}
//    with new ILPInference(new OJalgoHook())
//}