package org.recorder.nightfactory.domain;

public enum PaymentState {
    READY,
    PAID,
    FAILED,
    CANCELLED,
    PARTIAL_CANCELLED,
    VIRTUAL_ACCOUNT_ISSUED,
    ADMIN
}
