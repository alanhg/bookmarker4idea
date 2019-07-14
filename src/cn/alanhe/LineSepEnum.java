package cn.alanhe;

public enum LineSepEnum {

    HTML("<br>"),
    PLAIN_TEXT(null);

    private String seq;

    public String getSeq() {
        if (this.equals(PLAIN_TEXT)) {
            return System.getProperty("line.separator");
        }
        return seq;
    }

    LineSepEnum(String seq) {
        this.seq = seq;
    }
}
