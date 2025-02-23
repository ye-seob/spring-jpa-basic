package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
       // EntityManagerFactory는 애플리케이션 로딩 시점에 딱 하나만 생성돼야 함
       EntityManagerFactory  emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        // 트랜잭션 시작
        // JPA에서는 데이터 변경 작업이 트랜잭션 내에서 이루어져야 하므로 트랜잭션을 시작한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //트랜잭션을 trycatch문으로 감싸는 것이 좋음
        try {
            Member member = new Member();

            member.setId(1L);
            member.setName("John");

            em.persist(member);

            // 트랜잭션 커밋
            // 커밋은 실제 DB에 변경 사항을 반영하는 작업
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
