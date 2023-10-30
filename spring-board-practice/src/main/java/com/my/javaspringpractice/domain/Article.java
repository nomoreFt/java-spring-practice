package com.my.javaspringpractice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.*;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends BaseEntity{

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 500) private String title;
    @Setter @Column(nullable = false, length = 5000) private String content;
    @Setter @Column(nullable = false, length = 100) private String hashtag;

    @Setter
    @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC") @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() {}

    private Article(String title, String content, String hashtag, UserAccount userAccount) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
        this.userAccount = userAccount;
    }

    public static Article of(String title, String content, String hashtag, UserAccount userAccount) {
        return new Article(title, content, hashtag, userAccount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
