package ldg.study.springboot.orm.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author： ldg
 * @create date： 2019/1/14
 */
@Service
public interface TicketReposiory extends JpaRepository<Ticket, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into result(ticket_id,user_id) values(?1,?2) ", nativeQuery = true)
    void add(@Param("ticketId") Integer ticketId, @Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "update ticket t set t.count=t.count+(-1) where id=?1", nativeQuery = true)
    int intreduceCount(Integer id);

    /**
     * 查询信息
     */
    Ticket findTicketById(Integer id);
}
