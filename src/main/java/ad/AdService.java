package ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    @Autowired
    private AdMapper mapper;

    public int insertOK(AdVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(AdVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(AdVO vo){
        return mapper.deleteOK(vo);
    }
    public List<AdVO> searchList(String searchKey, String searchWord, int cpage, int limit){
        return mapper.searchList(searchKey, searchWord, cpage, limit);
    }
    public List<AdVO> selectAll(int cpage, int limit , AdVO vo) {
        return mapper.selectAll(cpage, limit , vo);
    }


}
