package cosreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosReviewService {

    @Autowired
    private CosReviewMapper mapper;

    public int insertOK(CosReviewVO vo){
        return mapper.insertOK(vo);
    }
    public int updateOK(CosReviewVO vo){
        return mapper.updateOK(vo);
    }
    public int deleteOK(CosReviewVO vo){
        return mapper.deleteOK(vo);
    }
    public List<CosReviewVO> selectAll(int cpage, int limit, CosReviewVO vo){
        return mapper.selectAll(cpage,limit,vo);
    }
    public CosReviewVO selectOne(CosReviewVO vo){
        return mapper.selectOne(vo);
    }
}
