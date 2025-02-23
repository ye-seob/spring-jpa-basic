package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// 만약 데베에는 USER로 저장돼있다면 @Table(name = "User")로 하면 됨
public class Member {
    @Id
    private long id;
    // 만약 데베에는 USER로 저장돼있다면 @Column(name = "username")로 하면 됨
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
