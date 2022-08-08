package william.redis.controller;

import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSet;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Auther: ZhangShenao
 * @Date 2022/8/8 10:16 AM
 * @Description: Redis操作API
 */
@RestController
public class OpsController {
    @Autowired
    private Redisson redisson;

    @GetMapping("/stats")
    public Map<String, String> stats() {
        //使用Redisson客户端操作map(hash)
        RMap<String, String> stats = redisson.getMap("stats");

        stats.put("pv", String.valueOf(100L));
        stats.put("uv", String.valueOf(50L));

        stats.addAndGet("click", 10L);

        return new HashMap<>(stats);
    }

    //查看朋友圈列表
    @GetMapping("wechat")
    public List<String> wechatMoments() {
        //使用Redisson Deque(list)实现FILO
        RDeque<String> wechat = redisson.getDeque("wechat-moments");
        wechat.push("周一:难受,不想上班");
        wechat.push("周三:小周末,简单放松一下");
        wechat.push("周五:马上周末了,开心");
        wechat.push("周六:享受周末");

        List<String> result = new ArrayList<>(wechat.size());

        while (!wechat.isEmpty()) {
            result.add(wechat.poll());
        }

        return result;
    }

    //抽奖
    @GetMapping("/luck_draw")
    public String luckDraw(@RequestParam("level") int level) {
        RSet<String> candidates = redisson.getSet("luck-draw");
        candidates.add("周杰伦");
        candidates.add("陶喆");
        candidates.add("孙燕姿");
        candidates.add("梁静茹");
        candidates.add("林俊杰");
        candidates.add("五月天");
        candidates.add("王心凌");
        candidates.add("蔡依林");
        candidates.add("李荣浩");
        candidates.add("陈奕迅");

        if (level == 3) {
            StringBuilder result = new StringBuilder("三等奖三名: \n");
            candidates.removeRandom(3).forEach(x -> result.append(x).append("\n"));
            return result.toString();
        }
        if (level == 2) {
            StringBuilder result = new StringBuilder("二等奖两名: \n");
            candidates.removeRandom(2).forEach(x -> result.append(x).append("\n"));
            return result.toString();
        }
        StringBuilder result = new StringBuilder("一等奖一名: \n");
        candidates.removeRandom(1).forEach(x -> result.append(x).append("\n"));
        return result.toString();
    }

    //员工OKR评分
    @GetMapping("/okr")
    public String okr() {
        RScoredSortedSet<String> okr = redisson.getScoredSortedSet("okr");
        okr.add(100D, "周杰伦");
        okr.add(100D, "陶喆");
        okr.add(100D, "李荣浩");
        okr.add(100D, "孙燕姿");
        okr.add(100D, "梁静茹");

        okr.addScore("周杰伦", 100L);   //周杰伦发新专辑,+100分
        okr.addScore("陶喆", -20D);    //陶喆出轨,-20分
        okr.addScore("李荣浩", 50D);    //李荣浩婚姻稳定,+50分
        okr.addScore("孙燕姿", 10D);    //孙燕姿开线上演唱会,+10分

        StringBuilder result = new StringBuilder("公布绩效结果:\r\n");
        //按绩效排名打分
        int i = 1;
        Collection<ScoredEntry<String>> scores = okr.entryRangeReversed(0, -1);
        for (ScoredEntry<String> s : scores) {
            result.append("第").append(i++).append("名: ").append(s.getValue()).append(", 得分: ").append(s.getScore()).append("\r\n");
        }
        return result.toString();
    }
}
