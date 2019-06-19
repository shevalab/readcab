package com.shevalab.readcab;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Requisite {
    private RequisiteType type = RequisiteType.NONE;
    private String content;
    private List<Requisite> dependencies;

    public RequisiteType getType() {
        return type;
    }

    public Requisite setType(RequisiteType type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Requisite setContent(String content) {
        this.content = content;
        return this;
    }

    public List<Requisite> getDependencies() {
        if(dependencies == null) {
            dependencies = new ArrayList<>();
        }
        return dependencies;
    }

    public void setDependencies(List<Requisite> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Requisite other = (Requisite) obj;
        return Objects.equals(type, other.type) && Objects.equals(content, other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }


    public List<Requisite> thisWithDependenciesRecursive() {
        List<Requisite> collectedWithDependencies = new ArrayList<>();
        if(dependencies != null) for(Requisite r : dependencies) {
            List<Requisite> depRecursive = r.thisWithDependenciesRecursive();
            collectedWithDependencies.addAll(depRecursive);
        }
        collectedWithDependencies.add(this);
        return collectedWithDependencies;
    }
}
