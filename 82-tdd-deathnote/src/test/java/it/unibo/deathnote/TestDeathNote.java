package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int ZERO = 0;
    private static final long SLEEP_TIME_FOR_CAUSE_OF_DEATH = 100;
    private static final long SLEEP_TIME_FOR_DETAILS = 6100;
    private static final String VICTIM = "Harold";
    private static final String VICTIM2 = "Jeff";
    private static final String EMPTY_STRING = "";
    private static final String DEFAULT_CAUSE_OF_DEATH = "heart attack";
    private static final String CUSTOM_CAUSE_OF_DEATH = "karting accident";
    private static final String CUSTOM_DETAILS = "ran too long";

    DeathNote deathNote;

    @BeforeEach
    public void setup() {
        deathNote = new DeathNoteImpl();
    }

    @Test
    public void testRuleNumbers() {
        var exception = assertThrows(
            IllegalArgumentException.class, 
            new Executable() {
                @Override
                public void execute() throws Throwable {
                    deathNote.getRule(ZERO);
                }
            }      
        );
        assertNotNull(exception.getMessage());
        assertFalse(exception.getMessage().isEmpty());
        assertFalse(exception.getMessage().isBlank());
    }

    @Test
    public void testRuleValidity() {
        for (final String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isEmpty());
            assertFalse(rule.isBlank());
        }
    }

    @Test
    public void testNameWritten() {
        assertFalse(deathNote.isNameWritten(VICTIM));
        deathNote.writeName(VICTIM);
        assertTrue(deathNote.isNameWritten(VICTIM));

        assertFalse(deathNote.isNameWritten(VICTIM2));
        assertFalse(deathNote.isNameWritten(EMPTY_STRING));
    }

    @Test
    public void testCauseOfDeath() throws InterruptedException {
        var exception = assertThrows(
            IllegalStateException.class, 
            new Executable() {
                @Override
                public void execute() throws Throwable {
                    deathNote.writeDeathCause(DEFAULT_CAUSE_OF_DEATH);
                }   
            }
        );
        assertNotNull(exception.getMessage());
        deathNote.writeName(VICTIM);
        assertEquals(DEFAULT_CAUSE_OF_DEATH, deathNote.getDeathCause(VICTIM));
        deathNote.writeName(VICTIM2);
        assertTrue(deathNote.writeDeathCause(CUSTOM_CAUSE_OF_DEATH));
        assertEquals(CUSTOM_CAUSE_OF_DEATH, deathNote.getDeathCause(VICTIM2));

        Thread.sleep(SLEEP_TIME_FOR_CAUSE_OF_DEATH);

        assertFalse(deathNote.writeDeathCause(DEFAULT_CAUSE_OF_DEATH));
        assertNotEquals(DEFAULT_CAUSE_OF_DEATH, deathNote.getDeathCause(VICTIM2));
        assertEquals(CUSTOM_CAUSE_OF_DEATH, deathNote.getDeathCause(VICTIM2));
    }
        
    @Test
    public void testDetails() throws InterruptedException {
        var exception = assertThrows(
            IllegalStateException.class, 
            new Executable() {
                @Override
                public void execute() throws Throwable {
                    deathNote.writeDetails(CUSTOM_DETAILS);
                }   
            }
        );
        assertNotNull(exception.getMessage());
        deathNote.writeName(VICTIM);
        assertEquals(EMPTY_STRING, deathNote.getDeathDetails(VICTIM));
        deathNote.writeName(VICTIM2);
        assertTrue(deathNote.writeDetails(CUSTOM_DETAILS));
        assertEquals(CUSTOM_DETAILS, deathNote.getDeathDetails(VICTIM2));

        Thread.sleep(SLEEP_TIME_FOR_DETAILS);

        assertFalse(deathNote.writeDetails(EMPTY_STRING));
        assertNotEquals(EMPTY_STRING, deathNote.getDeathCause(VICTIM2));
        assertEquals(CUSTOM_DETAILS, deathNote.getDeathDetails(VICTIM2));
    }
}