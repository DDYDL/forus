package service.my;

import util.PageInfo;

public interface PageService {

	PageInfo newPageInfo(Integer listsize, Integer curpage) throws Exception;
}
