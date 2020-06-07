# Persistence Context
> 기본 개념 정리

키워드와 해당 키워드들 끼리의 관계

- EMF (app 당 1)
- EM (Thread 당 1)
- Thread(@Transactional)
- EM : Persistence Context = 1:1
- Thrad : Persistence Context = 1:1
- EM : Thread = N : 1
- 1차 캐시, 지연쓰기 SQL(Dirty Check) : Persistence Context
- 2차캐시 : 어플리케이션 = 1 : 1

