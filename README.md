# 코테이토 첫 번째 백엔드 네트워킹

## 📜 Info

- 앞으로 백엔드 네트워킹에선 성능 향상, CS 지식 등을 스프링, 자바를 통해 함께 경험해보려 합니다.
- 이번 과제는 앞으로 네트워킹에서 기반이 될 프로젝트 코드를 작성하는 것입니다.
- [깃허브 레포지토리](https://github.com/IT-Cotato/9th-BE-Networking-1/tree/sample)
- 위 저장소를 fork 한 후 clone 해서 코드를 작성합니다.
- clone 후 새로운 branch를 만든 후 작업합니다.
- `DB 연결 등 환경 설정은 자유롭게 진행합니다.`
- 과제를 완료한 후 Pull Request 를 만든 후 하단의 표에 각자의 Pull Request 주소를 적어주세요.

## 📜 Scenario & **Requirement**

### 시나리오

- 코테이토 회원인 김감자의 부모님은 두 분이서 공인중개사 사무실을 운영하고 계십니다.
- 두 분은 손님들로부터 들어온 매물 정보를 엑셀에 기록하고 사무실 내부망을 통해 공유하고 있습니다.
- 그러던 어느날, 김감자의 부모님은 사업 확장을 위해 서로 다른 지역에서 사무실을 운영하게 되었습니다.
- 내부망을 통해 엑셀을 공유할 수 없게 되어 곤란해하시는 부모님을 본 김감자는 이를 해결하기 위해 DB와 REST API를 활용한 서버를 제작하기로 하였습니다.

### 사용 기술

- **Spring Data JPA**
- **MySQL**

### 요구사항

1. 현재까지 엑셀에 기록해둔 매물 정보들을 DB에 삽입합니다.
    - `real_estate` 라는 이름의 데이터베이스를 만듭니다.
    - `/api/test-data` 경로로 `POST` 요청을 보내면 아래의 파일을 파싱 후 Spring Data JPA를  사용해 `property` 테이블에 저장하는 컨트롤러를 작성합니다.
        [매물_정보 (1).xlsx](https://github.com/IT-Cotato/9th-BE-Networking-1/files/14632382/_.1.xlsx)

    - 엑셀 파일엔 아래와 같은 정보가 들어있습니다.
        1. 우편번호
            
            (서로 다른 건물이더라도 같은 우편번호를 가질 수 있습니다.)
            
        2. 시도
        3. 시군구
        4. 법정동명
        5. 지번본번
        6. 지번부번
        7. 도로명
        8. 건물번호 본번
        9. 건물번호 부번
    - DB엔 아래와 같이 저장합니다.
        1. 기본키 
            
            기본키는 Long 자료형으로 설정합니다.
            
        2. 우편번호
        3. 도로명 주소
            
            도로명 주소는
             `[시도] [시군구] [도로명] [건물번호 본번]-[건물번호 부번]` 
            
            형태로 저장합니다.
            
            ex) `서울특별시 은평구 증산서길 125-33` 
            
            단, 건물번호 부번은 없는 경우도 있습니다.
            
        4. 지번 주소
            
            지번 주소는 
            
            `[시도] [시군구] [법정동명] [지번본번]-[지번부번]` 
            
            형태로 저장합니다.
            
            ex) `서울특별시 은평구 갈현동 12-328` 
            
    - 엑셀 파싱엔 POI 라이브러리를 사용하며 아래의 글을 참고합니다
        
        [[SpringBoot] JAVA에서 POI를 이용하여 엑셀 파일 읽어오기](https://moongproject.tistory.com/5)
        
    
1. CRUD (Create, Read, Update, Delete)
    
    각 URI와 HTTP 메소드에 맞는 Controller, Service, Repository를 구현합니다.
    
    - `GET` `/api/properties?zip-code={우편번호}`
        
        해당 우편번호를 가지는 `매물'들`'의 정보를 반환합니다.
        
        ```json
        // "/api/properties?zip-code=03456" 에 대한 응답 예시
        
        {
          "properties": [
            {
              "id": 1,
              "zipCode": "03456",
              "roadNameAddress": "서울특별시 은평구 증산서길 125-33",
              "landLotNameAddress": "서울특별시 은평구 갈현동 12-328"
            },
            {
              "id": 3,
              "zipCode": "03456",
              "roadNameAddress": "서울특별시 은평구 은평로13길 244-34",
              "landLotNameAddress": "서울특별시 은평구 증산동 16-512"
            }
          ]
        }
        ```
        
    - `POST` `/api/properties`
        
        손님으로부터 들어온 `매물`을 요청받아 DB에 등록합니다. 
        
        ```json
        // "/api/properties"에 대한 요청 Body 예시
        {
          "zipCode" : "02344",
          "roadNameAddress" : "서울특별시 은평구 은평로13길 17-9",
          "landLotNameAddress" : "서울특별시 은평구 녹번동 156-15"
        }
        ```
        
        DB에서 해당 매물이 가지게 되는 id값을 응답합니다.
        
        ```json
        // "/api/properties"에 대한 응답 예시
        {
        	"id" : 7
        }
        ```
        
    - `DELETE` `/api/properies?road-name-address={도로명주소}`
        
        도로명 주소를 입력받아 해당 매물을 DB에서 삭제합니다.
        
        삭제 요청에 대한 응답 값은 스스로 고민 후 구현합니다.
        

### 정리

1. 엑셀을 파싱 후 DB에 저장하는 `POST` `/api/test-data` 
2. 우편번호에 해당하는 매물 정보를 응답하는  `GET` `/api/properties?zip-code={우편번호}` 
3. 매물을 등록하는 `POST` `/api/properties` 
4. 도로명주소에 해당하는 매물을 삭제하는 `DELETE` `/api/properies?road-name-address={도로명주소}` 
