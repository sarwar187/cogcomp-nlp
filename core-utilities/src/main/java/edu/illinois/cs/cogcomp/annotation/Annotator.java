/**
 * This software is released under the University of Illinois/Research and
 *  Academic Use License. See the LICENSE file in the root folder for details.
 * Copyright (c) 2016
 *
 * Developed by:
 * The Cognitive Computation Group
 * University of Illinois at Urbana-Champaign
 * http://cogcomp.cs.illinois.edu/
 */
package edu.illinois.cs.cogcomp.annotation;

import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.View;

/**
 * An interface for creating views of a specified name from a {@link TextAnnotation}
 *
 * @author Vivek Srikumar, Mark Sammons, Christos Christodoulopoulos
 */
public abstract class Annotator {


    protected String viewName;
    protected String[] requiredViews;


    /**
     * set the name of the View this Annotator creates, and the list of prerequisite Views that this
     * Annotator requires as input
     *
     * @param viewName The name of the View this annotator will populate. This will be used to
     *        access the created view from the TextAnnotation holding it.
     * @param requiredViews The views that must be populated for this new view to be created.
     */
    public Annotator(String viewName, String[] requiredViews) {
        this.viewName = viewName;
        this.requiredViews = requiredViews;
    }


    /**
     * create and add the view named by getViewName() to the TextAnnotation argument.
     *
     * @param ta the TextAnnotation to modify.
     */
    public abstract void addView(TextAnnotation ta) throws AnnotatorException;


    /**
     * return the name of the View created by this Annotator
     *
     * @return the name generated by this view.
     */
    public String getViewName() {
        return viewName;
    }


    /**
     * add the view named by getViewName() to the TextAnnotation argument, and return the View
     *
     * @param ta
     * @return the newly created View.
     * @throws AnnotatorException
     */
    public final View getView(TextAnnotation ta) throws AnnotatorException {
        addView(ta);
        return ta.getView(viewName);
    }

    /**
     * Can be used internally by {@link BasicAnnotatorService} to check for pre-requisites before
     * calling any single (external) {@link Annotator}.
     *
     * @return The list of {@link edu.illinois.cs.cogcomp.core.datastructures.ViewNames} required by
     *         this ViewGenerator
     */
    public String[] getRequiredViews() {
        return requiredViews;
    }

}