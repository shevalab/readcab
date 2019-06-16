package com.example.readcab;

import java.util.ArrayList;
import java.util.List;

public class RevisionToFile {
    private List<Long> revisions = new ArrayList<>();
    private List<String> files = new ArrayList<>();

    public List<Long> getRevisions() {
        return revisions;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getFile(Long revision) {
        int i = 0;
        while(i < revisions.size()) {
            if(revision < revisions.get(i)) {
                break;
            }
            i++;
        }
        return files.get(i-1);
    }
}
