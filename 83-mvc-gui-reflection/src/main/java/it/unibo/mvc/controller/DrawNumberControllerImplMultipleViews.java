package it.unibo.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * controllers that handles multiple views
 */
public class DrawNumberControllerImplMultipleViews implements DrawNumberController {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param model this is the model to use
     */
    public DrawNumberControllerImplMultipleViews(final DrawNumber model) {
        this.model = model;
        views = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newAttempt(final int n) {
        for (final DrawNumberView view : Objects.requireNonNull(views)) {
            view.result(model.attempt(n));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame() {
        this.model.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addView(final DrawNumberView newView) {
        views.add(newView);

        if (newView.getClass().equals(DrawNumberSwingView.class)) {
            newView.setController(this);
        }
        newView.start();
    }
}
