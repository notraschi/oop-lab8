package it.unibo.mvc.controller;

import java.util.ArrayList;
import java.util.Objects;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;

public class DrawNumberControllerImplMultipleViews implements DrawNumberController {

    private final DrawNumber model;
    private final ArrayList<DrawNumberView> views;

    public DrawNumberControllerImplMultipleViews(DrawNumber model) {
        this.model = model;      
        views = new ArrayList<>();  
    }

    @Override
    public void newAttempt(final int n) {
        for (DrawNumberView view : Objects.requireNonNull(views)) {
            view.result(model.attempt(n));
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    @Override
    public void addView(final DrawNumberView newView) {
        views.add(newView);
            
        newView.setController(this);
        newView.start();
    }
}