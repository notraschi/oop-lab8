package it.unibo.mvc.controller;

import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;

/**
 * This class implements the game controller. It orchestrates the game, exposes methods to its observers
 * (the boundaries), and sends results to them.
 */
public final class DrawNumberControllerImpl implements DrawNumberController {

    private final DrawNumber model;
    private DrawNumberView view;

    /**
     * Builds a new game controller provided a game model.
     *
     * @param model the implementation of the game model
     */
    public DrawNumberControllerImpl(final DrawNumber model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    // CHECKSTYLE: HiddenField OFF
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "this is a false positive")
    @Override
    public void addView(final DrawNumberView view) {
        Objects.requireNonNull(view, "Cannot set a null view");
        if (this.view != null) {
            throw new IllegalStateException("The view is already set! Multiple views are not supported");
        }
        this.view = view;
        view.setController(this);
        view.start();
    }
    // CHECKSTYLE: HiddenField ON

    /**
     * {@inheritDoc}
     */
    @Override
    public void newAttempt(final int n) {
        Objects.requireNonNull(view, "There is no view attached!").result(model.attempt(n));
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
    @SuppressFBWarnings(value = "DM_EXIT", justification = "req")
    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

}
