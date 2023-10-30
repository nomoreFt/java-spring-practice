package com.my.javaspringpractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Setter @Column(nullable = false, length = 500) private String content;

    @ManyToOne(optional = false) @Setter private Article article;
    @ManyToOne(optional = false) @JoinColumn(name = "userId") @Setter private UserAccount userAccount;

    protected ArticleComment() {}
    private ArticleComment(String content, Article article, UserAccount userAccount) {
        this.content = content;
        this.article = article;
        this.userAccount = userAccount;
    }

    public static ArticleComment of(String content, Article article, UserAccount userAccount) {
        return new ArticleComment(content, article, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
