package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

/**
 * this simple DeathNoteImpl allows to write one name at a time.
 */
public class DeathNoteImpl implements DeathNote {

    @Override
    public String getRule(int ruleNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean writeDetails(String details) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDeathCause(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDeathDetails(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isNameWritten(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}