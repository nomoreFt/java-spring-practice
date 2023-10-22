## 일정, 업무 관리

* github project
* github project-workflow

## 유스케이스, 엔티티 설계

* draw.io

## 기본 셋팅

* (깃 이그노어 파일 생성)[https://www.toptal.com/developers/gitignore/]
* JPA Buddy Plugin - JPA Entity, DTO 편하게 생성
* https://www.mockaroo.com/ - DB Test시에 예시 데이터 빠르게 생성

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