package com.shevalab.readcab;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RevisionToFileTest {

    @Test
    public void getRevisions() {
        RevisionToFile revisionToFile = new RevisionToFile();
        List<Long> revisions = revisionToFile.getRevisions();
        List<String> files = revisionToFile.getFiles();
        revisions.add(0L);
        revisions.add(5L);
        revisions.add(10L);
        files.add("0");
        files.add("5");
        files.add("10");

        assertEquals("0", revisionToFile.getFile(0L));
        assertEquals("0", revisionToFile.getFile(4L));
        assertEquals("5", revisionToFile.getFile(5L));
        assertEquals("5", revisionToFile.getFile(7L));
        assertEquals("10", revisionToFile.getFile(10L));
        assertEquals("10", revisionToFile.getFile(70L));
    }
}