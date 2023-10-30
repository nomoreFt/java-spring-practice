## 일정, 업무 관리

* github project
* github project-workflow

## 유스케이스, 엔티티 설계

* draw.io

## 기본 셋팅

* (깃 이그노어 파일 생성)[https://www.toptal.com/developers/gitignore/]
* JPA Buddy Plugin - JPA Entity, DTO 편하게 생성
* https://www.mockaroo.com/ - DB Test시에 예시 데이터 빠르게 생성

---

## @MappedSuperClass


* **공통 필드 또는 메소드**: 여러 Entity 클래스가 공통으로 가지는 필드나 메소드가 있다면, 이를 부모 클래스에 정의하고 @MappedSuperclass를 사용하여 이 클래스를 Entity 클래스의 공통 부분으로 사용할 수 있습니다.

* **상속 관계**: Entity 클래스 간의 상속 관계에서 부모 클래스가 데이터베이스 테이블으로 매핑되지 않아야 할 경우 @MappedSuperclass를 사용합니다. 즉, 부모 클래스는 테이블을 가지지 않고 하위 클래스에서 테이블을 가지도록 하려면 @MappedSuperclass를 붙입니다.

## @ManyToOne(optional = false)

@ManyToOne 애너테이션에 사용되는 optional 속성은 연관된 객체가 반드시 존재해야 하는지 여부를 나타냅니다.

* optional = true (기본값): 연관된 객체가 없어도 된다는 의미입니다. 즉, null이 허용됩니다.
* optional = false: 연관된 객체가 반드시 존재해야 함을 의미합니다. null을 허용하지 않습니다.
이 속성은 데이터베이스 스키마에 NOT NULL 제약 조건을 생성하고, JPA가 실행하는 SQL에 영향을 줄 수 있습니다. 또한, optional = false로 설정된 필드에 null 값을 넣으려고 하면, JPA는 예외를 발생시킵니다.

## SpringBoot3 QueryDsl Build.gradle

```groovy
dependencies {
//Querydsl 설정
    implementation "com.querydsl:querydsl-jpa:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
//Springboot3부터 버전 뒤에 뒤에 jakarta를 붙여야
    implementation 'com.querydsl:querydsl-core'
    implementation 'com.querydsl:querydsl-collections'
    annotationProcessor("com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
// java.lang.NoClassDefFoundError: javax.annotation.Generated 대응 코드
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
// java.lang.NoClassDefFoundError: javax.annotation.Entity 대응 코드

}
//Querydsl 설정 generated 폴더 생
//gradle과 ide의 QClass Build시에 생성 위치를 동일하게 하기 위해
def generated = "src/main/generated/querydsl"

//Querydsl java source set에 QClass 파일을 추가
tasks.withType(JavaCompile) {
    options.getGeneratedSourceOutputDirectory().set(file(generated))
}

//Querydsl QClass 파일 생성 위치를 지정
sourceSets {
    main.java.srcDirs += [generated]
}

//gradle clean 시에 QClass 디렉토리 삭제
clean.doLast {
    delete file(generated)
}

```

## QueryDsl + Spring Rest Data + Hal  기본 검색 구현

```java

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,//기본적으로 Entity 안에 있는 검색기능을 추가해준다.
        QuerydslBinderCustomizer<QArticle>//위에서 기본 구현된 검색 기능에 대소문자 구분, 부분검색 등 구체적인 검색기능을 달아준다.
{
}

```



## Querydsl QClass commit 제외 등록

.gitignore 파일에 build.gradle에서 설정한 QueryDsl QClass 경로 추가

~~~
### Querydsl QClass
spring-board-practice/src/main/generated
~~~


이후 .gitignore 적용되지 않을 경우 아래 명령어 수행

~~~
git rm -r --cached .
git add .
~~~

## View 그리기

게시판 리스트 참고
https://bootdey.com/snippets/view/General-Search-Results