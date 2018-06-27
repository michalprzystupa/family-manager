package familyManager.api.dto;

import java.util.List;

public class Family {
    private final Father father;
    private final List<Child> children;

    public Family(Father father, List<Child> children) {

        this.father = father;
        this.children = children;
    }

    public Father getFather() {
        return father;
    }

    public List<Child> getChildren() {
        return children;
    }
}
