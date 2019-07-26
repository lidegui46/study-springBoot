package ldg.study.springboot.orm.jdbcTemplate.web.api;

import ldg.study.springboot.orm.jdbcTemplate.service.TranscationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author foursix
 * @since 2017/11/10
 */
@RestController
@RequestMapping(value = "/transcational")
public class TranscationalController {
    @Autowired
    private TranscationalService transcationalService;

    @GetMapping(value = "/add")
    public void add() {
        transcationalService.add();
    }
}
