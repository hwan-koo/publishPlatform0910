# README

# 도서 창작 공유 플랫폼, 꿈꾸는 서재

AI의 도움을 받아 창작한 도서를 출판 및 관리, 공유할 수 있는 도서 창작 및 공유 플랫폼입니다. 

작가는 AI를 통해 창의적인 제안이나 스토리의 세부적인 전개 구상에 도움을 받아 손쉽게 도서를 제작 및 출판할 수 있습니다. 이를 통해 작가는 매력적인 콘텐츠를 지속적으로 개발할 수 있으며, 개인화된 창작 전략을 세울 수 있습니다. 출판된 도서는 플랫폼 내 다양한 어린이 독자들과 공유되며 독자들은 도서를 읽은 후 리뷰와 평점을 남겨 작가 및 타 독자들과 소통할 수 있습니다.

# 목차

---

# 서비스 시나리오

`기능적 요구사항`

1. 사용자와 작가는 가입을 할 수 있다.
2. 작가는 도서를 출판할 수 있다.
3. 작가는 제목과 간단한 줄거리를 입력해 스토리와 이미지를 AI로 생성할 수 있다.
4. 작가는 자신을 출판한 도서를 수정, 삭제할 수 있다.
5. 사용자는 도서를 결제 및 환불받을 수 있다.
6. 결제한 사용자는 ‘Home’페이지에서 전체 도서 목록을 확인할 수 있다.
7. 사용자는 자신이 결제한 도서를 ‘내 작품’ 페이지에서 영구적으로 조회할 수 있다.
8. 사용자는 도서에 대한 리뷰를 등록할 수 있다.
9. 리뷰는 도서별로 확인할 수 있다

`비기능적 요구사항`

1. 트랜잭션
    1. 결제가 되지 않은 경우 도서를 열람할 수 없다. Sync 호출
2. 장애격리
    1. AI스토리 생성이 작동하지 안더라도 AI이미지 생성은 365일 24시간 가능해야한다. Async (event-driven), Eventual Consistency 
3. 성능
    1. 사용자는 전체 도서 목록을 확인해 전체 도서의 상태를 확인할 수 있어야한다. CQRS

---

# 분석/설계

- 이벤트스토밍 결과:
    
    [https://www.msaez.io/#/110803716/storming/lanEdu](https://www.msaez.io/#/110803716/storming/lanEdu)
    

### 이벤트 도출
![image](https://github.com/user-attachments/assets/24addb87-0f1a-4b74-8a42-f0b1ab7278db)

### 액터, 커멘트, 폴리시 부착해 가독성 향상
![image 1](https://github.com/user-attachments/assets/7c1801fc-2370-4b62-9bc6-8a45ebaa6820)


### 어그리게잇으로 그룹화
![image 2](https://github.com/user-attachments/assets/9dc3b217-ff6d-4b2d-8650-febe5254f741)

- member와 bookPublish, genStory, genImage, bookReview,bookPurchase, paymentHistory는 그와 연결된 command와 event 들에 의해 트랜잭션이 유지되어야하는 단위로 그룹화하였다.

### 바운디드 컨텍스트로 그룹화
![image 3](https://github.com/user-attachments/assets/502e4639-3407-4e22-a7d4-ecd4e20e4bdf)

- 도메인 서열 분리
    - Core Domain: bookpublish, genstory, genimage: 생성형ai를 활용하여 작가들의 출판을 용이하게 해주는 핵심 서비스
    - Supporting Domain: review: 작가와 독자 간의 소통을 장려해 창작 공유 플랫폼의 장점을 극대화하여 경쟁력을 증진하기 위한 서비스
    - General Domain: member, bookpurchase, pay : 결제서비스로 3rd Party 외부 서비스를 사용하는 것이 경쟁력에 유리할 것으로 판단

### **컨텍스트 매핑 (점선은 Pub/Sub, 실선은 Req/Resp)**
![image 4](https://github.com/user-attachments/assets/729cec0d-e381-41b2-8f1c-4ae3ed403da2)


### **1차 완성본이 기능적/비기능적 요구사항을 커버하는지 검증**
![image 5](https://github.com/user-attachments/assets/affc3a85-74e1-447d-b25a-a07c99705fae)


- 작가는 도서를 출판할 수 있다.(ok)
- 작가는 제목과 간단한 줄거리를 입력해 스토리와 이미지를 AI로 생성할 수 있다. (x)
- 작가는 자신을 출판한 도서를 수정, 삭제할 수 있다.(ok)
- 사용자는 도서를 결제 및 환불받을 수 있다.(ok)
- 사용자는 후기를 등록할 수 있다.(ok)
- 결제한 사용자는 ‘Home’ 페이지에서 전체 도서 목록을 확인할 수 있다. (ok)
- 사용자는 자신이 결제한 도서를 ‘내 작품’ 페이지에서 영구적으로 조회할 수 있다. (ok)
- 사용자는 도서에 대한 리뷰를 등록할 수 있다.(ok)
- 리뷰는 도서별로 확인할 수 있다 (ok)

### 모형 수정
![image 6](https://github.com/user-attachments/assets/e44ba35f-460f-47e4-a64d-c8322fb639c2)

수정된 모델에서는 AI 생성 과정을 스토리 상태를 업데이트하는 Policy와 Image 상태를 업데이트 하는 Policy로 이원화하여 이미지 생성 중 오류 발생 시 생성했던 스토리가 유실되는 문제를 방지했다. 또한 Join할 테이블이 없는 관계로 AI스토리 및 AI이미지 생성을 받는 Policy 생성하였다.

- 사용자와 작가는 가입을 할 수 있다. (ok)
- 작가는 도서를 출판할 수 있다. (ok)
- 작가는 제목과 간단한 줄거리를 입력해 스토리와 이미지를 AI로 생성할 수 있다.(ok)
- 작가는 자신을 출판한 도서를 수정, 삭제할 수 있다. (ok)
- 사용자는 도서를 결제 및 환불받을 수 있다. (ok)
- 결제한 사용자는 ‘Home’ 페이지에서 전체 도서 목록을 확인할 수 있다. (ok)
- 사용자는 자신이 결제한 도서를 ‘내 작품’ 페이지에서 영구적으로 조회할 수 있다. (ok)
- 사용자는 도서에 대한 리뷰를 등록할 수 있다.(ok)
- 리뷰는 도서별로 확인할 수 있다 (ok)

---

# 구현

분석/설계 단계에서 도출된 헥사고날 아키텍처에 따라 각 BC별로 대변되는 마이로서비스들은 스프링부트로 구현하였다. 구현한 각 서비스를 로컬에서 실행하는 방법은 아래와 같다 (각자의 포트넘버는 8082 ~ 8088 이다)

```java
   mvn spring-boot:run
```

### API 게이트웨이

```java
  1. gateway 스프링부트 App을 추가 후 application.yaml내에 각 마이크로 서비스의 routes 를 추가하고 gateway 서버의 포트를 8080 으로 설정함
spring:
  profiles: docker
  cloud:
    gateway:
      routes:
        - id: bookpublish
          uri: http://bookpublish:8080
          predicates:
            - Path=/bookPublishes/**, 
        - id: bookpurchase
          uri: http://bookpurchase:8080
          predicates:
            - Path=/bookPurchases/**, 
        - id: member
          uri: http://member:8080
          predicates:
            - Path=/members/**, 
        - id: review
          uri: http://review:8080
          predicates:
            - Path=/bookReviews/**, 
        - id: genstory
          uri: http://genstory:8080
          predicates:
            - Path=/genStories/**, 
        - id: genimage
          uri: http://genimage:8080
          predicates:
            - Path=/genImages/**, 
        - id: pay
          uri: http://pay:8080
          predicates:
            - Path=/paymenthistories/**, 
        - id: frontend
          uri: http://frontend:8080
          predicates:
            - Path=/**
            
  2.  Kubernetes용 Deployment.yaml 을 작성하고 Kubernetes에 Deploy를 생성함
      - Deployment.yaml 예시
      apiVersion: apps/v1
				kind: Deployment
				metadata:
				  name: bookpublish
				  labels:
				    app: bookpublish
				spec:
				  replicas: 1
				  selector:
				    matchLabels:
				      app: bookpublish
				  template:
				    metadata:
				      labels:
				        app: bookpublish
				    spec:
				      containers:
				        - name: bookpublish
				          image: "wooha/bookpublish:0912"
				          ports:
				            - containerPort: 8080
				          readinessProbe:
				            httpGet:
				              path: '/actuator/health'
				              port: 8080
				            initialDelaySeconds: 10
				            timeoutSeconds: 2
				            periodSeconds: 5
				            failureThreshold: 10
				          livenessProbe:
				            httpGet:
				              path: '/actuator/health'
				              port: 8080
				            initialDelaySeconds: 120
				            timeoutSeconds: 2
				            periodSeconds: 5
				            failureThreshold: 5
		3. Kubernetes용 Service.yaml을 작성하고 Kubernetes에 Service/LoadBalancer을 생성하여 Gateway 엔드포인트를 확인함. 
      - Service.yaml 예시
      apiVersion: v1
				kind: Service
				metadata:
				  name: bookpublish
				  labels:
				    app: bookpublish
				spec:
				  ports:
				    - port: 8080
				      targetPort: 8080
				  selector:
				    app: bookpublish
```

		

### GenAI 프로세스

- **스토리 생성 프롬프트**
    <img width="696" alt="image 7" src="https://github.com/user-attachments/assets/a3fe093c-0709-4fb3-b1ff-980eed67d5a7">

    
- **이미지 생성 프롬프트**
    <img width="720" alt="image 8" src="https://github.com/user-attachments/assets/cfdc2746-9e52-4b71-851b-df3d53d2197f">

    

```java
- 프로세스 순서
1. 도서 생성
	{"eventType":"Published","timestamp":1726114939013,"id":1,"title":"test","contents":"asdf","imageUrl":null,"price":null,"memberId":null}
2. useAI 커맨드 -> GPT로 스토리 생성 요청 
	{"eventType":"AiUsed","timestamp":1726114972650,"bookId":1,"title":"test","contents":"{\"contents\": \"happy story\"}","memberId":null}
2. generateStory policy 실행
3. genStory 생성 → PostPersist로 카프카 메시지 생성 → generateImage ,StoryComplete
	{"eventType":"StoryGenerated","timestamp":1726115023361,"bookId":1,"story":"제목: '행복한 마을의 모험'\n\n옛날 옛날에 행복한 마을이 하나 있었어요. 그곳에는 항상 웃음소리와 즐거움이 가득했어요. 그러나 그 웃음소리와 즐거움이 흐르는 원동력은 무엇일까요?\n\n왕자라는 귀여운 작은 소년과 그의 가장 사랑스러운 친구, 몽이라는 강아지가 있었답니다. 왕자와 몽이는 항상 함께 노는 것을 좋아했으며, 그들의 웃음소리는 행복한 마을에 반짝이는 행복한 별빛이 되었습니다. 각 각의 모험은 마을 사람들에게 웃음과 즐거움으로 가득한 이야기를 선사했답니다. \n\n어느 날, 왕자와 몽이는 자신들의 가장 큰 모험을 준비하기로 했습니다. 그들은 광활한 숲, 반짝이는 강, 그리고 높은 산을 넘어 꽃이 만발한 골짜기로 가려고 했습니다.\n\n주어진 임무는 간단했습니다. 골짜기의 꽃들 중에서 가장 아름다운 꽃을 마을로 
    1. StoryComplete Policy로 BookPublish의 contents 필드 업데이트
4. generateImage Policy 실행
    1. GPT로 이미지 생성 요청
     바라는 마음을 담아 이름을 '행복의 나무'라고 지었습니다. 이제 그 나무는 모든 이에게 행복을 주는 나무, 우리의 마음에 사랑과 도움을 심어주는 행복의 나무가 되었답니다.\n\n그렇게 모두가 함께하는 속에서 행복은 가장 커지는 것, 그것이 바로 '행복의 나무'의 비밀이었습니다.","imageUrl":"https://oaidalleapiprodscus.blob.core.windows.net/private/org-EfT2WZht8DThpSZ7SiXZCibj/imageservice/img-UuQWa4ozzWGXGjFYmXAsDiUb.png?st=2024-09-12T04%3A30%3A51Z&se=2024-09-12T06%3A30%3A51Z&sp=r&sv=2024-08-04&sr=b&rscd=inline&rsct=image/png&skoid=d505667d-d6c1-4a0a-bac7-5c84a87759f8&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2024-09-11T23%3A26%3A53Z&ske=2024-09-12T23%3A26%3A53Z&sks=b&skv=2024-08-04&sig=yCOAdGVToRLSXBB/Eu3IGjETBjyqx%2BJlX/R9YpmU6ZU%3D"}
```

### 보상 트랙젝션 처리
![image 9](https://github.com/user-attachments/assets/987925ec-b657-4d64-bef7-d706e94488d5)


⇒ 3번의 retry 수행 초기 간격 1초

# 운영 및 배포

### Azure Kubernetes Service 사용

- 구독 확인 및 리소스 그룹 생성 → user03-rscrg
- Azure Kubernetes Service 생성
    - Kubernetes 클러스터 생성 → user03-aks
- 트러블 슈팅(kubectl 명령어 사용 안됨)
    1. **Gitpod 터미널에서 `kubectl` 설치**
        
        먼저, 터미널에서 `kubectl`을 다운로드하고 설치합니다.
        
        ```bash
        curl -LO "<https://dl.k8s.io/release/$>(curl -L -s <https://dl.k8s.io/release/stable.txt>)/bin/linux/amd64/kubectl"
        
        ```
        
    2. **실행 권한 부여**
        
        다운로드한 `kubectl` 파일에 실행 권한을 부여합니다.
        
        ```bash
        chmod +x kubectl
        
        ```
        
    3. **`kubectl`을 시스템 경로에 이동**
        
        `kubectl`을 시스템 경로에 있는 디렉토리로 이동하여, 명령어로 사용할 수 있게 합니다.
        
        ```bash
        sudo mv kubectl /usr/local/bin/
        
        ```
        
    4. **설치 확인**
        
        설치가 완료되면, `kubectl`이 제대로 설치되었는지 확인합니다.
        
        ```bash
        kubectl version --client
        
        ```
        
    
    이 단계를 완료한 후, 다시 `kubectl get node` 명령어를 실행해보세요. 정상적으로 작동해야 합니다.
    

### 배포

각 서비스들이 EDA 통신하기 위한 Kafka 서버를 내 클러스터에 설치후 각 서비스들 도커 파일로 빌드하여 배포 진행 완료
<img width="735" alt="image 10" src="https://github.com/user-attachments/assets/3b2b0f29-79ff-42e1-9a30-0db33a4e49d2">

