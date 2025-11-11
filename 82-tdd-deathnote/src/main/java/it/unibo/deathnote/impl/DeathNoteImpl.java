package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;

import it.unibo.deathnote.api.DeathNote;

/**
 * this simple DeathNoteImpl allows to write one name at a time.
 */
public class DeathNoteImpl implements DeathNote {

    private final HashMap<String, String[]> pages;
    private String latestName;
    private long nameWriteTime;
    private long causeWriteTime;

    private static final long TIME_WINDOW_DEATH_CAUSE = 40;
    private static final long TIME_WINDOW_DETAILS = 6040;
    private static final String DEFAULT_DEATH_CAUSE = "heart attack";

    public DeathNoteImpl() {
        this.pages = new LinkedHashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > DeathNote.RULES.size()) {
            throw new IllegalArgumentException("this isn't a valid rule number");
        }
        return DeathNote.RULES.get(ruleNumber +1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        latestName = name;
        nameWriteTime = System.currentTimeMillis();
        causeWriteTime = nameWriteTime;
        pages.put(name, new String[] {DEFAULT_DEATH_CAUSE, ""});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (latestName == null || cause == null) {
            throw new IllegalStateException("null cause of death or null name");
        } else if (System.currentTimeMillis() - nameWriteTime < TIME_WINDOW_DEATH_CAUSE) {
            pages.get(latestName)[0] = cause;
            causeWriteTime = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        if (latestName == null || details == null) {
            throw new IllegalStateException("null details or null name");
        } else if (System.currentTimeMillis() - causeWriteTime < TIME_WINDOW_DETAILS) {
            pages.get(latestName)[1] = details;
            return true;
        } else {
            return false;
        }
 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        if (pages.containsKey(name)) {
            return pages.get(name)[0];
        } else {
            throw new IllegalArgumentException("name provided isn't in the Death Note");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathDetails(final String name) {
        if (pages.containsKey(name)) {
            return pages.get(name)[1];
        } else {
            throw new IllegalArgumentException("name provided isn't in the Death Note");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        return pages.containsKey(name);
    }
}