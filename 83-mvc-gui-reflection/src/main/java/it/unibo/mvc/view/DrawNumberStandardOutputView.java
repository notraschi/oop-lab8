package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

/**
 * draws on stdout, does not handle input.
 */
public class DrawNumberStandardOutputView implements DrawNumberView {

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setController(final DrawNumberController observer) {
        throw new UnsupportedOperationException("this view is output only");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        // CHECKSTYLE: OFF
        System.out.println(res.getDescription()); // NOPMD
        // CHECKSTYLE: ON
    }
}
