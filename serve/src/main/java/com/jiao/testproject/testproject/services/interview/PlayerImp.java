package com.jiao.testproject.testproject.services.interview;

import com.jiao.testproject.testproject.dao.playerMapper;
import com.jiao.testproject.testproject.entity.player;
import com.jiao.testproject.testproject.entity.playerExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerImp  implements Player{

    private playerMapper playerMapper;

    @Autowired
    public PlayerImp(playerMapper pm){
        this.playerMapper = pm;
    }

    @Override
    public String getUsername() {
        playerExample playerExample = new playerExample();
        com.jiao.testproject.testproject.entity.playerExample.Criteria criteria = playerExample.createCriteria();
        criteria.andUsernameEqualTo("jiao");
        playerExample.setDistinct(true);
        List<player> players = playerMapper.selectByExample(playerExample);
        List<String> collect = players.stream().map(player::getUsername).collect(Collectors.toList());
        StringBuffer stringBuffer = new StringBuffer();
        collect.stream().forEach(x->{
            stringBuffer.append(x + "\t");
        });
        return stringBuffer.toString();
    }

    @Override
    public void write(String message) {

    }

    @Override
    public boolean isOffline() {
        return false;
    }
}
