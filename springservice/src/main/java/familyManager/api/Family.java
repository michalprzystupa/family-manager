package familyManager.api;

import java.util.List;

public class Family {
    private final long id;
    private final Father father;
    private final List<Child> children;

    public Family(long id, Father father, List<Child> children) {
        this.id = id;
        this.father = father;
        this.children = children;
    }

    public long getId() {
        return id;
    }

    public Father getFather() {
        return father;
    }

    public List<Child> getChildren() {
        return children;
    }
}
