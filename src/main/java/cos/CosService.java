package cos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosService {

    @Autowired
    private CosMapper mapper;

    public int insertOK(CosVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(CosVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CosVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CosVO> selectAll(int cpage, int limit, CosVO vo){
        return mapper.selectAll(cpage,limit,vo);
    }
    public CosVO selectOne(CosVO vo){
        return mapper.selectOne(vo);
    }
    public List<CosVO> searchList(String searchKey, String searchWord, int cpage, int limit){
        return mapper.searchList(searchKey,searchWord,cpage,limit);
    }
}
