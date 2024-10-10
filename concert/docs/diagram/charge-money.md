```mermaid
sequenceDiagram
    participant 유저서비스
    participant 토큰서비스
    participant 결제서비스
    유저서비스->>토큰서비스 : 대기열 토큰 검증
    토큰서비스->>토큰서비스 : 유효토큰 확인
    토큰서비스-->>유저서비스 : 유효토큰 여부 응답
    유저서비스->>결제서비스 : 잔액 충전(userId)
    결제서비스-->>유저서비스 : 잔액 충전 히스토리 응답
```