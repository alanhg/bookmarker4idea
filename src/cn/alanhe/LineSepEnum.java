package cn.alanhe;

public enum LineSepEnum {

    HTML("<br>"),
    PLAIN_TEXT(null);

    private String seq;

    public String getSeq() {
        return seq;
    }

    LineSepEnum(String seq) {
        this.seq = seq;
    }
}
