package com.xindong.mappers;

import com.xindong.entities.Collect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    int addCollection(Collect collect);

    int existSongId(Integer songId,Integer userId);

    int updateCollectMsg(Collect collect);

    int deleteCollect(Integer id);

    List<Collect> allCollect();

    List<Collect> myCollectOfSongs(Integer userId);
}