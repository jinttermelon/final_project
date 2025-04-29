package coslike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosLikeService {

    @Autowired
    private CosLikeMapper mapper;

    public int insertOK(CosLikeVO vo){
        return mapper.insertOK(vo);
    }
    public int deleteOK(CosLikeVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CosLikeVO> selectAll(int cpage, int limit, CosLikeVO vo){
        return mapper.selectAll(cpage,limit,vo);
    }
}
