package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLOutput;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManagerFactory는 애플리케이션 로딩 시점에 딱 하나만 생성돼야 함
        // hello 는 persistence.xml 의  <persistence-unit name="hello">와 일치해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드 간의 공유 X (사용하고 버리기)
        EntityManager em = emf.createEntityManager();


        // 트랜잭션 시작
        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //트랜잭션을 trycatch문으로 감싸는 것이 좋음
        try {
            Member member1 = new Member(10L, "예섭");
            Member member2 = new Member(11L, "예섭3");

            em.persist(member1);
            em.persist(member2);
            //이 선 위에서 쿼리가 날라가지 않음
            // 영속성 저장만으로는 SQL쿼리 X
            System.out.println("=========");

            // 트랜잭션 커밋
            // 커밋은 실제 DB에 변경 사항을 반영하는 작업
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
