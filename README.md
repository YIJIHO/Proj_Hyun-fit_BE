## Hyun-fit

### 1. About The Project
  - 홈 트레이닝 웹 서비스 플랫폼, 모션인식 트레이닝과 라이브 화상트레이닝 서비스를 제공
|개발기간|2023.08 - 2023.10 (8주)|
|--------|-----------------------|

### 2. 

### 3. Specification
![Java](https://img.shields.io/badge/Java-9932cc?style=for-the-badge&logo=java&logoColor=white&labelColor=gray&label=17)
![Spring Boot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white&labelColor=gray&label=3.2.2)
![Oracle](https://img.shields.io/badge/Oracle-DC382D?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-e9967a?style=for-the-badge&logo=github&logoColor=white)
![IntelliJ](https://img.shields.io/badge/IntelliJ-6a5acd?style=for-the-badge&logo=intellij-idea&logoColor=white)
### 3. ERD


### 4. Architecture


### 5. File Structure
```plaintext
Hyun-Fit-BE
 |-->ApiGateway
 |-->Docker
 |     └>docker-compose.yml
 |-->UserModule
 |-->ActiveModule
 |-->NewsfeedModule
 |-->ProductModule
 |-->OrderModule
 └-->Managing-StockModule
```
### 6. API Documentation
//개별 블로그에 첨부

### 7. Command
```plaintext
//docker컨테이너 올리기 (인스턴스 생성) 
docker-compose up

//docker컨테이너 내리기 (인스턴스 삭제)
docker-compose down

//docker컨테이너 시작 (기존 인스턴스 시작)
docker-compose start

//docker컨테이너 정지 (기존 인스턴스 정지)
docker-compose stop

//docker network 생성
docker network create {네트워크이름}

//docker network 확인
docker network inspect {네트워크이름}

//docker network 연결
docker network connect {네트워크이름} {컨테이너이름}
```
