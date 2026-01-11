package com.aloha.board.dto;

/**
 * Enum 역할
 * : board 같은 문자열 직접 쓰지 않고
 *  타입으로 관리해서 오타 방지 및 유지보수 쉽게 하려는 목적
 */
public enum ParentTable {
    BOARD("board");
    // Enum 안에 하나의 값 BOARD 선언
    // : 내부적으로 board 문자열 가지고 있음

    private final String tableName;

    ParentTable(String tableName) {
        this.tableName = tableName;
    }
    // Enum 값 선언할 때 전달한 board 여기로 들어옴
    public String value() {
        return tableName;
    }
    // Enum 갖고 있는 문자열 값 꺼내는 메서드
    // : ParentTable.BOARD.value() -> "board"
}
