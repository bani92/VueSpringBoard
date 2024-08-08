package com.example.vuespringboard.utils;

public enum CommonEnum {

    REG_SUCCESS("success","등록 되었습니다."),
    DEL_SUCCESS("success","삭제 되었습니다."),

    VIEW_FAIL("fail","조회에 실패하였습니다."),
    REG_FAIL("fail","등록에 실패하였습니다."),
    DEL_FAIL("fail","삭제에 실패하였습니다."),
    DEL_TOTAL_FAIL("fail","%s"),
    REG_TOTAL_FAIL("fail","%s");

    private final String code;

    private final String description;

    CommonEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescription(Object... args) {
        return String.format(description, args);
    }
}
