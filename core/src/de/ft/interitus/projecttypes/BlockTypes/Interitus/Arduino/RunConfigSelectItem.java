package de.ft.interitus.projecttypes.BlockTypes.Interitus.Arduino;

public class RunConfigSelectItem {

    private String content;
    private String identifier;

    public RunConfigSelectItem(String content, String identifier) {
        this.content = content;
        this.identifier = identifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return content;
    }
}
