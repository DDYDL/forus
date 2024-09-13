package service.my;

import util.PageInfo;

public class PageServiceImpl implements PageService {
	// 리스트를 받으면 pageinfo에 정보를 넣어서 반환
	public PageInfo newPageInfo(Integer listsize, Integer curpage) throws Exception {
		PageInfo pageInfo = new PageInfo();
		
		Integer allPage = (int)Math.ceil((double)listsize/10);
		//startPage : 1~10 => 1, 11~20 => 11
		Integer startPage = (curpage-1)/10*10+1;
		Integer endPage = startPage+10-1;
		if(endPage>allPage) endPage = allPage;
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setCurPage(curpage);
		
//		Integer row = (pageInfo.getCurPage()-1)*10+1;
		
		return pageInfo;
	}
}
