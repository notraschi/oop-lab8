package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImplMultipleViews;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws SecurityException yo
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws NoSuchMethodException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImplMultipleViews(model);
        /*
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());
        */
        final String[] viewsToLoad = {
            "it.unibo.mvc.view.DrawNumberSwingView",
            "it.unibo.mvc.view.DrawNumberStandardOutputView",
        };
        try {
            for (final String viewName : viewsToLoad) {
                final Class<? extends DrawNumberView> view = Class.forName(viewName).asSubclass(DrawNumberView.class);
                final Constructor<? extends DrawNumberView> constructor = view.getConstructor();
                for (int i = 0; i < 3; i++) {
                    app.addView(constructor.newInstance());
                }
            }
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final InvocationTargetException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final SecurityException e) {
            e.printStackTrace();
        }
    }
}
