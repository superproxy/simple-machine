package cn.yxz.machine;

public enum OperationCode {
    READ(10, "读输入到内存"),
    WRITE(11, "写内存"),
    LOAD(20, "加载"),
    STORE(21, "存"),
    ADD(30, "+"),
    SUBSTRACT(31, "-"),
    DIVIDE(32, "/"),
    MULTIPLY(33, "x"),
    BRANCH(40, "x"),
    BRANCH_NEG(41, "x"),
    BRANCHN_ZERO(42, "x"),
    HALT(43, "halt");


    int code;
    String desc;

    private OperationCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OperationCode getByValue(int code) {
        for (OperationCode operationCode : OperationCode.values()) {
            if (operationCode.code == code) {
                return operationCode;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "OperationCode{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
