package com.RBook.board.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //클래스가 만들어지지 않는 기초 클래스라는 뜻 공통 매핑 정보를 포함하는 클래스임을 의미
public abstract class Time {

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @PrePersist//JPA 엔티티가 저장되기 전에 실행할 메서드 지정
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate //JPA 엔티티가 수정되기 전에 실행할 메서드 지정
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now();
    }
}
