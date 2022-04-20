package com.example.demo.entity.enums;

public enum Classes {
    BIR(1), IKKI(2), UCH(3), TORT(4), BESH(5), OLTI(6),
    YETTI(7), SAKKIZ(8), TOQQIZ(9), ON(10), ONNBIR(11);
    final int value;

    Classes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
