package kr.co.koscom.olympus.pb.ab.db.page;

public interface PBPageData<P extends PBPage> {

    P getPage();

    PBPageData<P> setPage(P page);
}
