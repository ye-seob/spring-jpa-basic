package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("Jack");

            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member m : result) {
                System.out.println(m.getName());
            }

            // em.persist(findMember); 안 해도 됨
            // JPA를 통해 엔티티를 가져오면 관리해주기 때문에
            // 변경사항이 생기면 알아서 update 쿼리를 날려줌


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
