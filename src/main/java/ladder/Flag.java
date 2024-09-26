package ladder;

public enum Flag {

    AFTER("After"),
    BEFORE("Before");


    private final String flag;

    Flag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
